var serverPath = "localhost:8088";
function getArticleTable() {
    $.post(
        serverPath + "/admin/articleList",
        null,
        function (retData) {
            if (retData.code == 1){
                var htmlStr ="";
            }
        }
    )
}
function deleteById(id) {
    
}
function getAddPage() {
    
}