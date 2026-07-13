var accounts = [];
var v_idUpdate = -1;
var vTheme = "";
var baseUrl = "http://localhost:8085/api/v1/accounts";
var baseUrlDepartment = "http://localhost:8085/api/v1/departments";
var baseUrlPosition = "http://localhost:8085/api/v1/positions";
var baseAvt =
    "https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg";

loadData();
loadDepartment();
loadPosition();

vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

function changeTheme(themeValue) {
    if (themeValue === "dark") {
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }
    localStorage.setItem("theme", themeValue);
}

function loadData() {
    $.ajax({
        type: "GET",
        url: baseUrl,
        dataType: "JSON",
        success: function (response) {
            accounts = response;
            var tableContent = "";
            for (let i = 0; i < accounts.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + accounts[i].id + "</td>";
                tableContent +=
                    "<td><img src=" +
                    baseAvt +
                    " style='height: 50px' alt='Image' /></td>";
                tableContent += "<td>" + accounts[i].username + "</td>";
                tableContent += "<td>" + accounts[i].fullName + "</td>";
                tableContent += "<td>" + accounts[i].departmentName + "</td>";
                tableContent += "<td>" + accounts[i].positionName + "</td>";
                tableContent +=
                    "<td><button onclick='onHandleEdit(" +
                    accounts[i].id +
                    ")'>Edit</button> " +
                    " <button onclick='onDelete(" +
                    accounts[i].id +
                    ")'>Delete</button></td>";
                tableContent += "</tr>";
            }
            $("#tableBody").empty();
            $("#tableBody").append(tableContent);
        },
        error: function (error) {
            alert("Call api get accounts thất bại");
        },
    });
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");
    if (check) {
        $.ajax({
            type: "DELETE",
            url: baseUrl + "/" + idDelete,
            success: function (response) {
                alert("Xóa thành công!");
                loadData();
            },
            error: function (error) {
                alert("Call api xóa thất bại");
            },
        });
    }
}

function onCreate() {
    if (v_idUpdate > 0) {
        alert("Đang update, ko thể tạo mới dc");
        return;
    }
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();

    var account = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };

    $.ajax({
        type: "POST",
        url: baseUrl,
        data: JSON.stringify(account),
        contentType: "application/json",
        success: function (response) {
            alert("Thêm dữ liệu thành công");
            loadData();
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputEmail").val("");
            $("#modal-id").modal("hide");
        },
        error: function (error) {
            alert("Call api thêm mới thất bại");
        },
    });
}

$("#submit").click(function (e) {
    if (v_idUpdate <= 0) {
        onCreate();
    } else {
        onUpdate();
    }
});

function resetForm() {
    $(".modal-title").empty();
    $(".modal-title").append("<div>Create Account</div>");
    $("#inputAvatar").val("");
    $("#inputUsername").val("");
    $("#inputFullname").val("");
    $("#inputAge").val("");
    v_idUpdate = -1;
}

function onHandleEdit(idUpdate) {
    $("#modal-id").modal("show");
    $.ajax({
        type: "GET",
        url: baseUrl + "/" + idUpdate,
        dataType: "JSON",
        success: function (response) {
            $(".modal-title").empty();
            $(".modal-title").append("<div>Update Account</div>");
            $("#inputAvatar").val(response.avatar);
            $("#inputUsername").val(response.username);
            $("#inputFullname").val(response.fullName);
            $("#inputEmail").val(response.email);
            $("#inputDepartmentName").val(response.departmentId);
            $("#inputPositionName").val(response.positionId);
            v_idUpdate = idUpdate;
        },
        error: function (error) {
            alert("Call api lấy thông tin thất bại");
        },
    });
}

function onUpdate() {
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();
    var accountUpdate = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };
    $.ajax({
        type: "PUT",
        url: baseUrl + "/" + v_idUpdate,
        data: JSON.stringify(accountUpdate),
        contentType: "application/json",
        success: function (response) {
            alert("Update dữ liệu thành công");
            loadData();
            v_idUpdate = -1;
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputAge").val("");
            $("#modal-id").modal("hide");
        },
        error: function (error) {
            alert("Call api update thất bại");
        },
    });
}

function loadDepartment() {
    $.ajax({
        type: "GET",
        url: baseUrlDepartment,
        dataType: "JSON",
        success: function (response) {
            var content = "";
            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputDepartmentName").empty();
            $("#inputDepartmentName").append(content);
        },
        error: function (error) {
            alert("Call api get department thất bại");
        },
    });
}

function loadPosition() {
    $.ajax({
        type: "GET",
        url: baseUrlPosition,
        dataType: "JSON",
        success: function (response) {
            var content = "";
            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputPositionName").empty();
            $("#inputPositionName").append(content);
        },
        error: function (error) {
            alert("Call api get position thất bại");
        },
    });
}
