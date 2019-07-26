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

          //监听工具条
          table.on('tool(ruleTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
              layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
              });
            } else if(obj.event === 'edit'){
               xadmin.open('添加权限','./rule/edit/',400,450)
//              layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
          });


            /*用户-停用*/
            function member_stop(obj,id){
                layer.confirm('确认要停用吗？',function(index){

                    if($(obj).attr('title')=='启用'){

                      //发异步把用户状态进行更改
                      $(obj).attr('title','停用')
                      $(obj).find('i').html('&#xe62f;');

                      $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                      layer.msg('已停用!',{icon: 5,time:1000});

                    }else{
                      $(obj).attr('title','启用')
                      $(obj).find('i').html('&#xe601;');

                      $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                      layer.msg('已启用!',{icon: 5,time:1000});
                    }

                });
            }

            $("#pn").focus(function(){
              $("#test9").css("background-color","#FFFFCC");
            });
});