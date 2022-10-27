console.log("memberAdd");

$("#all").click(function(){
   let ch = $(this).prop("checked");
   $(".check").prop("checked", ch);
})

$(".check").click(function(){
    let allcheck = true;    
    $(".check").each(function(index, c){
           let check = $(this).prop("checked");
           if(!check){
            allcheck = false;
            return;
           }
           console.log(index + 1,check);
    })
    $("#all").prop("checked", allcheck)
})

// 선생님 code
// $(".check").click(function(){
//     $(".check").each(function(index, item){
//         let ch = $(item).prop("checked")
//         console.log(ch);
//     })
// })

// $(".check").click(function(){
//     $("#all").prop("checked", true)

//     $(".check").each(function(index, c){
//            let check = $(this).prop("checked");
//            if(!check){
//             $("#all").prop("checked", fasle)
//             return;
//            }
//            console.log(index + 1,check);
//     })
//     $("#all").prop("checked", allcheck)
// })

///////                                                     ID CHECK
// id, pw, pw==pw2, name, email
let results = [false, false, false, false, false];

$("#id").blur(function(){
    let id  = $("#id").val();
    let result = nullCheck(id.length >= 2, $("#idr"), "ID는");
    results[0]=result;

    //단, id가 ''비어있지 않을 때                                      IDCHECK AJAX
    $.get("./idCheck?id="+id, function(data){
        console.log("data : ", data);
        if(data == '0'){
            $("#udr").html("사용 가능한 ID입니다")
            results[0]=true;
        }else{
            $("#udr").html("이미 사용중인 ID입니다")
            results[0]=false
        }
    })
    
});
// $("#pw").change(function(){
//     $("#pw2").val("");
//     result[2]=equals($("#pw").val(), $("#pw2").val());
//     $("#pwr2").html("비밀번호가 일치하지 않습니다")
// })
// $("#pw").blur(function(){
//     let result = nullCheck($("#pw").val().length >=2, $("#pwr"), "PW는");
//     results[1]=result;  
    
//     //처음 비번을 일치하는지 확인 후 비번을 재 입력을 할 시에 pw2를 지워줌,,,하지만 문제있음
//     // $("#pw2").val("");
//     // reuslts[2]=false; 
//     // $("#pwr2").html("");
// })
$("#pw2").blur(function(){
    let result = equals($("#pw").val(), $("#pw2").val());
    results[2]=result;
    if(!result){
        $("#pwr2").html("비밀번호가 일치하지 않습니다")
    }
})

$("#pw").on({
    blur:function(){
             let result = nullCheck($("#pw").val().length >=2, $("#pwr"), "PW는");
             results[1]=result;  
    },
    change:function(){
        $("#pw2").val("");
        result[2]=equals($("#pw").val(), $("#pw2").val());
        $("#pwr2").html("비밀번호가 일치하지 않습니다")
    }
});

$("#name").blur(function(){
    let result = nullCheck($("#name").val().length >=2, $("#namer"), "이름은");
    results[3]=result;
})
$("#email").blur(function(){
    let result = nullCheck($("#email").val().length >=2,  $("#emailr"), "이메일은");
    results[4]=result;
})

$("#joinButton").click(function(){
    if(results.includes(false)){
        alert("필수 입력을 다해야지 이새캬")
    }else{
        $("#joinForm").submit();
    }
    // let c = true;
    // $.each(results, function(idx, item){
    //     if(!item){
    //         alert("필수 입력란을 확인해주세요")
    //         c = false;
    //         return false;
    //     }
    // })
    // if(c){
    //     $("#joinForm").submit();
    // }
})