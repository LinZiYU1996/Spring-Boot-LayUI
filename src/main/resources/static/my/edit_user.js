/**
 * Created by linziyu on 2019/2/7.
 */



layui.use(['layer', 'form'], function(){
    var layer = layui.layer
        ,form = layui.form;

    $('#user-name').blur(function() {
        var name = $(this).val();
        var id = $('#user-id').val();
        $.ajax({
            url:'/u/getOldUser',
            type:'post',
            dataType:'json',
            async: false,//保证ajax执行后才往下执行
            data:{id:id},
            //验证用户名是否可用
            success:function(data){

            }
        });
        $.ajax({
            url:'/u/checkName',
            type:'post',
            dataType:'json',
            async: false,//保证ajax执行后才往下执行
            data:{name:name},
            //验证用户名是否可用
            success:function(data){
                if (data.code == 101){
                    return;
                }
                if (data.code == 200) {
                    $('#ri').removeAttr('hidden');
                    $('#wr').attr('hidden','hidden');


                } else {
                    $('#wr').removeAttr('hidden');
                    $('#ri').attr('hidden','hidden');
                    layer.msg('当前用户名已被占用! ');

                }

            }
        })

    });


    function checkName() {
        var name = $('#user-name').val();
        var flag = 1;

        $.ajax({
            url: '/u/checkName',
            type: 'post',
            dataType: 'json',
            async: false,//保证ajax执行后才往下执行
            data: {name: name},
            //验证用户名是否可用
            success: function (data) {
                if (data.code == 101) {
                    return flag = 101;
                }
                if (data.code == 102) {
                    return flag = 102;
                }
                flag = 200;
            }
        });
        return flag;

    }

    $("#update-btn").on("click", function () {
        var layer=layui.layer;
        var flag = checkName();
        if (flag == 102){
            layer.msg("用户名已经存在，如需更改请用其它名字");
            return;
        }

        $.ajax({
            url: "/u/update_user",
            type: "post",
            async: false,//保证ajax执行后才往下执行
            dataType: "json",
            data: {
                id: $('#user-id').val(),
                name: $('#user-name').val(),
                password: $('#user-password').val()
            },
            success: function (result) {
                if (result.code == 200){
                    layer.alert("更新成功！",function(){
                        window.parent.location.reload();//刷新父页面
                        parent.layer.close(index);//关闭弹出层
                    });
                    return;
                }
                layer.msg("NO");
            }
        })
    })
});