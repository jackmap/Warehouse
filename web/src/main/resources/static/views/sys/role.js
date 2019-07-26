 layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
          var laydate = layui.laydate //日期
          ,laypage = layui.laypage //分页
          ,layer = layui.layer //弹层
          ,table = layui.table //表格
          ,carousel = layui.carousel //轮播
          ,upload = layui.upload //上传
          ,element = layui.element //元素操作
          ,slider = layui.slider //滑块

        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
        //监听指定开关
         form.on('switch(sexDemo)', function(obj){
           layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
         });
});


