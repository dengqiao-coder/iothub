var elem = "currentTableId";
var filter = "currentTableFilter";

function initPage(option) {
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form;
        table = layui.table;
        table.render({
            elem: '#' + elem,
            url: option.searchUrl,
            cols: option.col,
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
            var w = ($(window).width() * 0.99);
            var h = ($(window).height() - 50);
            layer.open({
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: true,
                maxmin: true,
                scrollbar: false,
                title: '新增',
                content: option.addUrl
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
                request($, table, layer, {"ids": ids}, option.deleteUrl);
            });
        });

        table.on('tool(' + filter + ')', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var w = ($(window).width() * 0.99);
                var h = ($(window).height() - 50);
                layer.open({
                    type: 2,
                    area: [w + 'px', h + 'px'],
                    fix: true,
                    maxmin: true,
                    scrollbar: false,
                    title: '编辑',
                    content: option.editUrl + "?id=" + data.id
                });
            } else if (obj.event === 'delete') {
                layer.confirm('确定删除选中的行吗？', function () {
                    var ids = data.id;
                    request($, table, layer, {"ids": ids}, option.deleteUrl);
                });
            }
        });


    });
}


function request($, table, layer, data, url) {
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

function sub(url) {
    layui.use(['form'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        verify(form);
        form.on('submit(*)', function (data) {
            request($, parent.table, parent.layer, data.field, url);
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