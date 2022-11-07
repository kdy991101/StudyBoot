let count = 1;


function setCount(c){
    if(c<1 || c>5){
        c =1;
    }
    count = c;
}

$("#fileAdd").click(function(){

    if(flag){
        let size = $("#files".attr("data-file-size"));
        if(size == undefined){
            size=0;
            count= size;
            flag = false;
        }
    }
    
    if(count < 5){
        let add = '<div class="mb-3">' 
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

let flag = true;
//글 수정시 삭제
$(".deleteFile").click(function(){
    let check = confirm("지울거냐? 다시생각해");

    if(flag){
        let size = $("#files".attr("data-file-size"));
        if(size == undefined){
            size=0;
            count= size;
            flag = false;
        }
    }

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
                count--;
            }
        })

    }else{
        alert("잘 생각했다 인마")
    }
}) 