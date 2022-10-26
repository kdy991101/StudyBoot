console.log("start");

$("#btn").click(function(){
    console.log("btn click");
})

// const buttons = document.getElementsByClassName("buttons");
// // or
// const buttons = document.querySelectorAll(".buttons");
// element를 모아둔 배열 반복문 돌려서 하나씩 꺼내야 함
// jquery는 다름

$(".buttons").click(function(){//반복문 돌리지 않아도 ㄱㅊㄱㅊ              
    console.log("buttons");
})

$("#test").on("click", "#btn2", function(){
    
});