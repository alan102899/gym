var data_list=[];
var now_pid="";

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
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td><a href="#" onclick="OpenBuy('${item.pid}')">Buy</a></td>
                    </tr>
                `;
            });
            $("#content_list").html(str);
        }
    });
}

function OpenBuy(pid){
    now_pid=pid;
    $.ajax({
        url:"http://localhost:8091/member/all",
        method:"GET",
        success:function(res){
            console.log(res);
            var str="";
            res.memberList.forEach(item=>{
                str+=`
                    <option value="${item.mid}">${item.firstname} - ${item.lastname}</option>
                `;
            });
            $("#member_id").html(str);
            $("#AddModal").modal('show');
        }
    });
}

$(function(){
    load();
});


function save(){
    var member_id=$("#member_id").val();
    if(!member_id  ){
        alert("The content cannot be empty.");
        return;
    }
    $.ajax({
        url:"http://localhost:8091/pay/",
        contentType: "application/json",
        method:"POST",
        data:JSON.stringify({"pid":now_pid,"mid":member_id}),
        success:function(res){
            $("#AddModal").modal('hide');
            load();
            alert("Pay Successfully!  The current member balance is:"+res.order.member.balance);
        },
        error:function(res){
            $("#AddModal").modal('hide');
            alert(res.responseJSON.message);
        }
    });
}

