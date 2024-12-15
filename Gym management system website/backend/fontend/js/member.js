var data_list=[];
var now_mid="";
function OpenTopUp(mid){
    now_mid=mid;
    $('#TopUpModal').modal('show');
}

function openDetails(mid){
    var now_data={};
    data_list.forEach(item=>{
        if(item.mid==mid){
            now_data=item;
        }
    });
    $("#MemberDetailsBox").html(`
        <p><strong>Member ID:</strong> ${now_data.mid}</p>
        <p><strong>Member First Name:</strong> ${now_data.firstname}</p>
        <p><strong>Member Last Name:</strong> ${now_data.lastname}</p>
        <p><strong>Member Email:</strong> ${now_data.email}</p>
        <p><strong>Member Phone:</strong> $ ${now_data.phone}</p>
        <p><strong>Member Birthday:</strong> ${now_data.birthday}</p>
        <p><strong>Member Address:</strong> ${now_data.address}</p>
        <p><strong>Member Balance:</strong> $ ${now_data.balance}             
            <button type="button" class="btn btn-primary" onclick="OpenTopUp('${now_data.mid}')">Top up</button>
        </p>
        <p>
            <button type="button" class="btn btn-primary" onclick="OpenEdit('${now_data.mid}')">Edit</button>
        </p>
    `);
    $('#offcanvasRight').offcanvas('show');
    console.log(now_data);
}

function OpenEdit(mid){
    var now_data={};
    data_list.forEach(item=>{
        if(item.mid==mid){
            now_data=item;
        }
    });

    $("#MemberDetailsBox").html(`
        <div class="mb-3">
            <label for="mid" class="form-label">Member ID:</label>
            <input type="text" class="form-control" id="ed_mid" name="ed_mid" placeholder="" value="${now_data.mid}">
        </div>
        <div class="mb-3">
            <label for="firstname" class="form-label">First Name:</label>
            <input type="text" class="form-control" id="ed_firstname" name="ed_firstname" placeholder="" value="${now_data.firstname}">
        </div>
        <div class="mb-3">
            <label for="lastname" class="form-label">Last Name:</label>
            <input type="text" class="form-control" id="ed_lastname" name="ed_lastname" placeholder="" value="${now_data.lastname}">
        </div>
        <div class="mb-3">
            <label for="birthday" class="form-label">Birthday:</label>
            <input type="text" class="form-control" id="ed_birthday" name="ed_birthday" placeholder="MM/DD/YYYY" value="${now_data.birthday}">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone:</label>
            <input type="text" class="form-control" id="ed_phone" name="ed_phone" placeholder="" value="${now_data.phone}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="ed_email" name="ed_email" placeholder="" value="${now_data.email}">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <input type="text" class="form-control" id="ed_address" name="ed_address" placeholder="" value="${now_data.address}">
        </div>
        <div class="mb-3">
            <label for="balance" class="form-label">Balance:</label>
            <input type="number" class="form-control" id="ed_balance" name="ed_balance" placeholder="" value="${now_data.balance}">
        </div>
        <div class="">
            <button type="button" class="btn btn-secondary"  onclick="openDetails('${now_data.mid}')">Back</button>
            <button type="button" class="btn btn-primary" onclick="EditSave()">Save</button>
        </div>
    `);
}



function load(){
    $.ajax({
        url:"http://localhost:8091/member/all",
        method:"GET",
        success:function(res){
            console.log(res);
            var str="";
            data_list=res.memberList;
            data_list.forEach(item=>{
                str+=`
                    <tr>
                        <td>${item.mid}</td>
                        <td style="cursor:pointer;text-decoration: underline;color: blue;" onclick="openDetails('${item.mid}')">${item.firstname} - ${item.lastname}</td>
                        <td>${item.birthday}</td>  
                        <td>$ ${item.balance}</td>  
                        <td>
                            <a href="#" onclick="del('${item.mid}')">Delete</a>
                        </td>  
                    </tr>
                `;
            });
            $("#content_list").html(str);
        }
    });
}

function del(mid){
    $.ajax({
        url:"http://localhost:8091/member/"+mid,
        method:"DELETE",
        success:function(res){
           alert(res.message);
           search();
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
            url:"http://localhost:8091/member/name/"+search_txt,
            method:"GET",
            success:function(res){
                var str="";
                data_list=res.memberList;
                data_list.forEach(item=>{
                    str+=`
                        <tr>
                            <td>${item.mid}</td>
                            <td style="cursor:pointer;text-decoration: underline;color: blue;" onclick="openDetails('${item.mid}')">${item.firstname} - ${item.lastname}</td>
                            <td>${item.birthday}</td>  
                            <td>$ ${item.balance}</td>  
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
    var mid=$("#mid").val();
    var firstname=$("#firstname").val();
    var lastname=$("#lastname").val();
    var birthday=$("#birthday").val();
    var phone=$("#phone").val();
    var email=$("#email").val();
    var address=$("#address").val();
    var balance=$("#balance").val();

    if(!mid || !firstname || !lastname || !birthday || !phone || !email || !address || !balance){
        alert("The content cannot be empty.");
        return;
    }
    $.ajax({
        url:"http://localhost:8091/member/",
        contentType: "application/json",
        method:"POST",
        data:JSON.stringify({"mid":mid,"firstname":firstname,"lastname":lastname,"birthday":birthday,"phone":phone,"email":email,"address":address,"balance":balance}),
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


function EditSave(){
    var mid=$("#ed_mid").val();
    var firstname=$("#ed_firstname").val();
    var lastname=$("#ed_lastname").val();
    var birthday=$("#ed_birthday").val();
    var phone=$("#ed_phone").val();
    var email=$("#ed_email").val();
    var address=$("#ed_address").val();
    var balance=$("#ed_balance").val();

    if(!mid || !firstname || !lastname || !birthday || !phone || !email || !address || !balance){
        alert("The content cannot be empty.");
        return;
    }
    $.ajax({
        url:"http://localhost:8091/member/",
        contentType: "application/json",
        method:"PUT",
        data:JSON.stringify({"mid":mid,"firstname":firstname,"lastname":lastname,"birthday":birthday,"phone":phone,"email":email,"address":address,"balance":balance}),
        success:function(res){
            alert(res.message);
           window.location.reload();
        },
        error:function(res){
            alert(res.responseJSON.message);
            window.location.reload();
        }
    });
}

function TopUp(){
    var money=$("#money").val();
    $.ajax({
        url:"http://localhost:8091/member/"+now_mid+"/topUp/"+money,
        contentType: "application/json",
        method:"PUT",
        success:function(res){
            $('#TopUpModal').modal('hide');
            $('#offcanvasRight').offcanvas('hide');
            load();
            alert(res.message);
        },
        error:function(res){
            alert(res.responseJSON.message);
            window.location.reload();
        }
    });
}
