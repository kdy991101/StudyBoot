let count = 0;
$("#fileAdd").click(function(){
    
    if(count < 5){
        let add = '<div class="mb-3" >' 
        add = add+'<input type="file" class="form-control" name="files">';
        add = add+'<button type="button" class="del">X</button>'
        add = add+'</div>'
        $("#files").append(add);
        count++;
    }else{
        alert("최대 5개");
        

    }

})

$("#files").on("click", ".del", function(){
    $(this).parent().remove();
    count--;
});

$(".deleteFile").click(function(){
    let check = confirm("지울거냐? 다시생각해");

    if(check){
        //post
        //qna/fileDelete
        //fileNum
        let fileNum = $(this).attr("data-file-num");
        const btn = $(this)

        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("result : ",result);
                $(btn).parent().remove();
            }
        })

    }else{
        alert("잘 생각했다 인마")
    }
})