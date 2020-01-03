function initPage(elem, col, searchUrl, addUrl, editUrl, deleteUrl) {
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form;
        table = layui.table;
        table.render({
            elem: '#' + elem,
            url: searchUrl,
            cols: col,
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true
        });

        form.on('submit(data-search-btn)', function (data) {
            var result = data.field;
            //执行搜索重载
            table.reload(elem, {
                page: {
                    curr: 1
                }
                , where: result
            }, 'data');
            return false;
        });


        $(".data-add-btn").on("click", function () {
            //layer.msg('添加数据');
            var w = ($(window).width() * 0.99);
            var h = ($(window).height() - 50);
            layer.open({
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: true, //不固定
                maxmin: true,
                scrollbar: false,
                title: '新增',
                content: addUrl
            });
        });

        $(".data-delete-btn").on("click", function () {
            var checkStatus = table.checkStatus(elem)
                , data = checkStatus.data;
            if (data.length == 0) {
                layer.alert("请选择要删除的记录！");
                return false;
            }
            layer.confirm('确定要删除选中的' + data.length + "条记录吗？", function () {
                var ids = "";
                for (x in data) {
                    ids += data[x].id + ",";
                }
                ids = ids.substring(0, ids.length - 1);
                request($, elem, table, layer, {"ids": ids}, deleteUrl);
            });
        });
    });
}


function request($, elem, table, layer, data, url) {
    $.ajax({
        url: url,
        type: 'POST',
        async: true,
        data: data,
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                layer.alert(result.msg, {
                    icon: 1
                });
                var index = layer.getFrameIndex(window.name);
                if (index != undefined) {
                    layer.close(index);
                }
                table.reload(elem, {
                    page: {
                        curr: 1
                    }
                }, 'data');
            } else {
                layer.alert(result.msg, {
                    icon: 2
                });
            }
        },
        beforeSend: function () {
            layer.msg('数据提交中,请稍后...', {
                icon: 16,
                shade: 0.3,
                time: 0
            });
        },
        complete: function () {
            layer.closeAll('loading');
        }
    });
}

function sub(elem, url) {
    layui.use(['form'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        verify(form);
        form.on('submit(*)', function (data) {
            request($, elem, parent.table, parent.layer, data.field, url);
            return false;
        });
    });
}

function verify(form) {
    form.verify({
        code: function (value, item) {
            if (!/^[a-zA-Z0-9]{4,16}$/.test(value)) {
                return '请输入4-16位的英文或数字';
            }
        },
        password: function (value, item) {
            if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,18}$/.test(value)) {
                return '密码为6-18个字符，至少1个大写字母，1个小写字母和1个数字';
            }
        }
    });
}