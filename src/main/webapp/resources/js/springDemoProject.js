/**
 * Created by nishat on 10/23/15.
 */
$(function () {

    $('#signUp').click(function () {
        $('#successMessage').html("");
        $('#signUpForm').dialog("open");
    });

    $('#logIn').click(function () {
        if (!$("input[name='userEmail']").val() || !$("input[name='userPassword']").val()) {
            return showErrorMessage("logInError", "Please Enter Email and Password");
        }
        $("#logInForm").submit();
    });

    var showErrorMessage = function (divId, errorMsg) {
        $("#" + divId).html(errorMsg);
        $("#" + divId).show();
        return false;
    };

    var buttons = [
        {
            text: "Submit",
            click: function () {

                if ($("input[name='pass']").val() && !$("input[name='confPass']").val()) {
                    return showErrorMessage('signUpError', "Please Re-Type Password");
                }
                if (!($("input[name='pass']").val() === $("input[name='confPass']").val())) {
                    return showErrorMessage('signUpError', "Password Did Not Matched")
                }

                var url = "/SpringDemoProject/registration"
                clearFieldErrors();
                postData(url);
            }
        },
        {
            text: "Cancel",
            click: function () {
                return closeSignUpWindow()
            }
        }
    ];

    var closeSignUpWindow = function () {
        $('#signUpForm').dialog("close");
        return true
    };

    function clearFieldErrors() {
        $("span[id='firstName']").html("");
        $("span[id='lastName']").html("");
        $("span[id='email']").html("");
        $("span[id='password']").html("");
    }

    $("#signUpForm").dialog({
        title: "Sign Up",
        autoOpen: false,
        height: "auto",
        width: 500,
        show: 'slide',
        buttons: buttons,

        open: function (event, ui) {
            $('#signUpForm').css('overflow', 'hidden');
        },

        beforeClose: function (event, ui) {
            $("input[name='firstName']").val("");
            $("input[name='lastName']").val("");
            $("input[name='sex']").val("");
            $("input[name='email']").val("");
            $("input[name='pass']").val("");
            $("input[name='confPass']").val("");
            $('.sex').prop('checked', false);
            clearFieldErrors();
            $('#signUpError').hide();
        }
    });

    function postData(url) {
        var userData = {
            "firstName": $("input[name='firstName']").val(),
            "lastName": $("input[name='lastName']").val(),
            "email": $("input[name='email']").val(),
            "password": $("input[name='pass']").val(),
            "gender": $("input[name=gender]:checked").val()
        };

        $.ajax({
            url: url,
            data: JSON.stringify(userData),
            type: "POST",
            contentType: "application/json",
            dataType: "json",

            success: function (data) {

                switch (data.type) {
                    case 'Error':
                        showMessages(data.message)
                        break;
                    case 'Success':
                        showMessages(data.message)
                        return closeSignUpWindow();
                }
            },

            error: function (xhr, textStatus, error) {
                alert(xhr.statusText);
                alert(textStatus);
                alert(error);
            }
        });

        function showMessages(map) {
            $.each(map, function (index, value) {
                $("#" + index).html(value);
            });
        }
    }
});