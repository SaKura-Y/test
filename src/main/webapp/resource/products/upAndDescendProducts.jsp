<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>上下架</title>
    <meta charset="utf-8" >
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resource\js\modernizr-custom-v2.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

    <style type="text/css">
        body{
            background: white;
        }

        table{

        }



    </style>

</head>
<body>
<form action="showProducts" method="post" enctype="multipart/form-data">
    <div id="showProducts">
        <table id="products" border="1px red">
            <tr class="pinfo">
                <th>商品id</th>
                <th>商品名称</th>
                <th>商品类型</th>
                <th>商品数量</th>
                <th>商品管理</th>
            </tr>
        </table>
    </div>
</form>
</body>

<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"showProducts",
            type:"post",
            success:function (data) {
                console.log(data);
                for (var i=0;i<data.length;i++){
                    var pid=data[i].pId;
                    var product='<tr class="product">'+
                                '<th>'+
                                "<h5>"+data[i].pId+"</h5>"+
                                '</th>'+
                                '<th>'+
                                "<h5>"+data[i].pName+"</h5>"+
                                '</th>'+
                                '<th>'+
                                "<h5>"+data[i].pType+"</h5>"+
                                '</th>'+
                                '<th>'+
                                "<h5>"+data[i].pNum+"</h5>"+
                                '</th>'+
                                '<th>'+
                                '<a id="upProduct" pid="'+data[i].pId+'"> '+"<p>上架</p>"+'</a>'+
                                "&ensp;&ensp;&ensp;&ensp;"+
                                '<a id="descendProduct" pid="'+data[i].pId+'"> '+"<p>下架</p>"+'</a>'+
                                '</th>'+
                                '</tr>'
                    $("#products").append(product);
                }
            }

        });

        $("#products").on("click","#upProduct p",function () {
            $.ajax({
                url:"upProduct",
                type:"post",
                data:{
                    "pid":$(this).parent().attr("pid")
                }
            });
        });
        $("#products").on("click","#descendProduct p",function () {
            $.ajax({
                url:"descendProduct",
                type:"post",
                data:{
                    "pid":$(this).parent().attr("pid")
                }
            });
        });

    });


</script>
</html>
