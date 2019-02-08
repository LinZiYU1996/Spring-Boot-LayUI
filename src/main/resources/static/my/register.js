/**
 * Created by linziyu on 2019/2/7.
 */

layui.use(['form','jquery','layer'], function () {
    var form   = layui.form;
    var $      = layui.jquery;
    var layer  = layui.layer;
    //添加表单失焦事件
    //验证表单
    $('#name').blur(function() {
        var name = $(this).val();
//            alert(name);
        //alert(user);
        $.ajax({
            url:'/u/checkRegisterName',
            type:'post',
            dataType:'json',
            data:{name:name},
            //验证用户名是否可用
            success:function(data){
                if (data.code == 304) {
                    $('#ri').removeAttr('hidden');
                    $('#wr').attr('hidden','hidden');


                } else {
                    $('#wr').removeAttr('hidden');
                    $('#ri').attr('hidden','hidden');
                    layer.msg('当前用户名已被占用! ')

                }

            }
        })

    });
    function checkName() {
        var name = $('#name').val();
        var flag = true;
        $.ajax({
            url:'/u/checkRegisterName',
            type:'post',
            dataType:'json',
            async: false,//保证ajax执行后才往下执行
            data:{name:name},
            //验证用户名是否可用
            success:function(data){
                if (data.code == 304) {//false表示没有被占有
                    alert("304");
                    flag = false;


                } else {
//                        $('#wr').removeAttr('hidden');
//                        $('#ri').attr('hidden','hidden');
//                        layer.msg('当前用户名已被占用! ')

                }

            }
        });
        return flag;

    }



    // you code ...
    // 为密码添加正则验证
    $('#pwd').blur(function() {
        var reg = /^[\w]{1,12}$/;
        if(!($('#pwd').val().match(reg))){
            //layer.msg('请输入合法密码');
            $('#pwr').removeAttr('hidden');
            $('#pri').attr('hidden','hidden');
            layer.msg('请输入合法密码');
        }else {
            $('#pri').removeAttr('hidden');
            $('#pwr').attr('hidden','hidden');
        }
    });

    //验证两次密码是否一致
    $('#rpwd').blur(function() {
        if($('#pwd').val() != $('#rpwd').val()){
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden','hidden');
            layer.msg('两次输入密码不一致!');
        }else {
            $('#rpri').removeAttr('hidden');
            $('#rpwr').attr('hidden','hidden');
        };
    });

    //
    //添加表单监听事件,提交注册信息
    form.on('submit(sub)', function(data) {
        var flag = checkName();
        if (flag == true){
            layer.msg("用户名已经被占有了");
            return;
        }
        $.ajax({
            url:'/u/register',
            type:'post',
            dataType:'json',
            data:{
                name:$('#name').val(),
                password:$('#pwd').val()
            },
            success:function(data){
                if (data.code == 200 ) {
                    layer.msg('注册成功');
                    ///location.href = "login.html";
                }else {
                    layer.msg('注册失败');
                }
            }
        });
        //防止页面跳转
        return false;
    });

});
