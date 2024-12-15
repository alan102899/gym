var data_list=[];


function load(){
    $.ajax({
        url:"http://localhost:8091/product/all",
        method:"GET",
        success:function(res){
            console.log(res);
            var str="";
            data_list=res.productList;
            data_list.forEach(item=>{
                str+=`
                    <tr>
                        <td>${item.pid}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                    </tr>
                `;
            });
            $("#content_list").html(str);
        }
    });
}

$(function(){
    load();
});

function search(){
    var search_txt=$("#search_txt").val();
    if(search_txt){
        $.ajax({
            url:"http://localhost:8091/product/name/"+search_txt,
            method:"GET",
            success:function(res){
                var str="";
                data_list=res.productList;
                data_list.forEach(item=>{
                    str+=`
                        <tr>
                            <td>${item.pid}</td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                        </tr>
                    `;
                });
                $("#content_list").html(str);
            }
        });
    }else{
        load();
    }
}

function save(){
    var pid=$("#pid").val();
    var name=$("#name").val();
    var price=$("#price").val();
    if(!pid || !name || !price ){
        alert("The content cannot be empty.");
        return;
    }
    $.ajax({
        url:"http://localhost:8091/product/",
        contentType: "application/json",
        method:"POST",
        data:JSON.stringify({"pid":pid,"name":name,"price":price}),
        success:function(res){
            $("#AddModal").modal('hide');
            alert(res.message);
            load();
        },
        error:function(res){
            $("#AddModal").modal('hide');
            alert(res.responseJSON.message);
        }
    });
}

