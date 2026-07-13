var departments = [];
var v_deptIdUpdate = -1;
var baseUrldepartment = "http://localhost:8085/api/v1/departments";

vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

loadDepartmentTable();

function changeTheme(themeValue) {
    if (themeValue === "dark") {
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }
    localStorage.setItem("theme", themeValue);
}

function loadDepartmentTable() {
    $.ajax({
        type: "GET",
        url: baseUrldepartment,
        dataType: "JSON",
        success: function (response) {
            departments = response;
            var tableContent = "";
            for (let i = 0; i < departments.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + departments[i].id + "</td>";
                tableContent += "<td>" + departments[i].name + "</td>";
                tableContent +=
                    "<td><button class='btn btn-warning btn-sm' onclick='onHandleEdit(" +
                    departments[i].id +
                    ")'>Edit</button> " +
                    " <button class='btn btn-danger btn-sm' onclick='onDelete(" +
                    departments[i].id +
                    ")'>Delete</button></td>";
                tableContent += "</tr>";
            }
            $("#tableBody").empty();
            $("#tableBody").append(tableContent);
        },
        error: function (error) {
            alert("Call API lấy danh sách phòng ban thất bại!");
        },
    });
}

$("#submitDept").click(function (e) {
    if (v_deptIdUpdate <= 0) {
        onCreate();
    } else {
        onUpdate();
    }
});

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn muốn xóa phòng ban này?");
    if (check) {
        $.ajax({
            type: "DELETE",
            url: baseUrldepartment + "/" + idDelete,
            success: function (response) {
                alert("Xóa phòng ban thành công");
                loadDepartmentTable();
            },
        });
    }
}

function onCreate() {
    var v_deptartmentName = $("#inputDeptName").val();

    var dept = {
        name: v_deptartmentName,
    };

    $.ajax({
        type: "POST",
        url: baseUrldepartment,
        data: JSON.stringify(dept),
        contentType: "application/json",
        success: function (response) {
            alert("thêm dữ liệu thành công!");
            loadDepartmentTable();
            $("#inputDeptName").val();
            $("#modal-dept-id").modal("hide");
        },
        error: function (error) {
            alert("Call api thêm mới thất bại");
        },
    });
}

function onHandleEdit(idUpdate) {
    $("#modal-dept-id").modal("show");
    $.ajax({
        type: "GET",
        url: baseUrldepartment + "/" + idUpdate,
        dataType: "JSON",
        success: function (response) {
            $(".modal-dept-title").empty();
            $(".modal-dept-title").append("<div>Update Department</div>");
            $("#inputDeptName").val(response.name);
            v_deptIdUpdate = idUpdate;
        },
        error: function (error) {
            alert("Không thể lấy thông tin phòng ban");
        },
    });
}

function onUpdate() {
    var v_deptName = $("#inputDeptName").val();
    var deptUpdate = {
        name: v_deptName,
    };

    $.ajax({
        type: "PUT",
        url: baseUrldepartment + "/" + v_deptIdUpdate,
        data: JSON.stringify(deptUpdate),
        contentType: "application/json",
        success: function (response) {
            alert("Update dữ liệu thành công");
            loadDepartmentTable();
            v_deptIdUpdate = -1;
            $("#inputDeptName").val("");
            $("#modal-dept-id").modal("hide");
        },
        error: function (error) {
            alert("API cập nhật phòng ban thất bại!");
        },
    });
}

function resetDeptForm() {
    $(".modal-dept-title").empty();
    $(".modal-dept-title").append("<div>Create Department</div>");
    $("#inputDeptName").val("");
    v_deptIdUpdate = -1;
}
