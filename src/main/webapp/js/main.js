"strict";

function postAjax() {
    return $.ajax({
        type: "POST"
        , url: "/ajax"
        , contentType: "application/json"
        , data: JSON.stringify({
            init: new Date()
            , end: new Date()
            , message: " hello from client "
        })
    });
}

function postForm() {
    return $.post("/entity", {
        init: new Date()
        , end: new Date()
        , message: " hello from client "
    })
}
