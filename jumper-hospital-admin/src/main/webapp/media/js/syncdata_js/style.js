 function g(obj)
  	{
     var lis=document.getElementsByClassName("table_style");
     for(var i=0;i<lis.length;i++) {
     if(lis[i]!=obj){
     	lis[i].style.background="#eff2f9";
     	lis[i].style.color="#3B485F";
     }
     else{
     	lis[i].style.background="#909BAF";
      	lis[i].style.color="white";
     }
      
    }
   };