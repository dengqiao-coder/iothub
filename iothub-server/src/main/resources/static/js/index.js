layui.config({
    base: "js/",
    version: true
}).extend({
    layuimini: "layuimini"
}).use(['element', 'layer', 'layuimini'], function () {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer;

    layuimini.init('controller/init.json');

    $('.login-out').on("click", function () {
        layer.msg('退出登录成功', function () {
            window.location = '/page/login-1.html';
        });
    });
});