var elem = "currentTableId";
var searchUrl = "/device/list";
var addUrl="/page/device/add";
var editUrl="/page/device/edit";
var deleteUrl="/device/delete";
var table;
var col = [
    [
        {type: 'checkbox', width: 50, fixed: 'left'},
        {field: 'id', title: 'ID', hide: true},
        {field: 'productName', title: '产品编码'},
        {field: 'deviceName', title: '终端编码'},
        {field: 'brokerUsername', title: '帐号'},
        {field: 'secret', title: '密码'},
        {field: 'updateTime', title: '最后修改时间'},
        {
            title: '操作', fixed: 'right', align: 'center', templet: function (data) {
                var operationStr = "<a class=\"layui-btn layui-btn-xs data-count-edit\" lay-event=\"edit\">编辑</a>";
                operationStr += "<a class=\"layui-btn layui-btn-xs layui-btn-danger data-count-delete\" lay-event=\"delete\">删除</a>";
                return operationStr;
            }
        }
    ]
]
initPage(elem,col,searchUrl,addUrl,editUrl,deleteUrl);