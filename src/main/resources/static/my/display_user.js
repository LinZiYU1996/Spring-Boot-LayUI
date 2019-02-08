/**
 * Created by linziyu on 2019/2/6.
 */


layui.use(["form","table","element"], function(){
    var $ = layui.jquery;
    var element = layui.element;
    var table = layui.table;
    var form = layui.form;
    $("#logout").click(function () {
        layer.confirm('真退出么', function(index){
            $.ajax({
                url:'/u/logout',
                type:'post',
                dataType:'json',
                success:function(data){
                    if (data.code == 200){
                        window.location.href = "/u/loginpage";
                    }
                }
            });
            layer.close(index);
        });



        });
    $("#display-users").click(function () {
        document.getElementById('2').className='layui-body hide';//
        document.getElementById('3').className='layui-body hide';//
        document.getElementById('4').className='layui-body hide';//
        document.getElementById('1').className='layui-body';//替换成一个新class
        table.render({
            elem: '#test'
            ,url:'/u/getAllUser'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID'}
                ,{field:'name', title:'用户名'}

                ,{field:'password', title:'密码'}

                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
            ,limit:5
        });
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        alert(checkStatus+'asasasas');
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
        }
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;//获取User数据
        var old = data.name;

        if(obj.event === 'del'){//对应删除事件
            layer.confirm('真的删除行么', function(index){
                $.ajax({
                    url:'/u/deleteUser',
                    type:'post',
                    dataType:'json',
                    data:{
                        id:data.id,
                        name:data.name,
                        password:data.password
                    },
                    success:function(data){
                        if (data.code == 200 ) {
                            layer.alert("删除成功！",function(){
                                window.parent.location.reload();//刷新父页面
                                parent.layer.close(index);//关闭弹出层
                            });
                        }else {
                            layer.msg('删除失败');
                        }
                    }
                });
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2,//坑
                title:'ass',
                area: ['500px', '300px'],
                content:'/u/edit_user',
                success:function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    // 获取子页面的iframe
                    var iframe = window['layui-layer-iframe' + index];
                    // 向子页面的全局函数child传参
                    iframe.get(data.name);
                    body.find("#user-name").val(data.name);
                    body.find("#old-name").val(old);
                    body.find("#user-password").val(data.password);
                    body.find("#user-id").val(data.id);
                    form.render();
                }
            });
        }
    });
});