/**
 * Created by linziyu on 2019/2/3.
 */


/**
 * Created by linziyu on 2019/2/3.
 */


layui.use(["form","table","element"], function(){
    var $ = layui.jquery;
    var element = layui.element;
    var table = layui.table;
    var form = layui.form;
    popForm=layui.form;
    $("#display-register-log").click(function () {
        // alert("aaa");
        document.getElementById('1').className='layui-body hide';//替换成一个新class
        document.getElementById('2').className='layui-body hide';//替换成一个新class
        document.getElementById('4').className='layui-body hide';//替换成一个新class
        document.getElementById('3').className='layui-body';//替换成一个新class
        table.render({
            elem: '#registerlog'
            ,url:'/u/getAllRegisterLog'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID'}
                ,{field:'registerUserName', title:'新注册用户名'}

                ,{field:'registerTime', title:'注册时间',templet:function (data) {
                    return showTime(data.registerTime);}}


                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
            ,limit:5
        });
    });

    function showTime(tempDate)
    {
        var d = new Date(tempDate);
        var year = d.getFullYear();
        var month = d.getMonth();
        month++;
        var day = d.getDate();
        var hours = d.getHours();

        var minutes = d.getMinutes();
        var seconds = d.getSeconds();
        month = month<10 ? "0"+month:month;
        day = day<10 ? "0"+day:day;
        hours = hours<10 ? "0"+hours:hours;
        minutes = minutes<10 ? "0"+minutes:minutes;
        seconds = seconds<10 ? "0"+seconds:seconds;


        var time = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        return time;
    }



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
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;//获取User数据


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
                content:'/u/lay',

                success:function (layero, index) {

                    var body = layui.layer.getChildFrame('body', index);

                    body.find("#user-name").val(data.name);
                    body.find("#user-password").val(data.password);
                    body.find("#user-id").val(data.id);
                }
            });

        }
    });
});