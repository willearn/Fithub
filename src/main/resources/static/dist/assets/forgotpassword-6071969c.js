import{_ as p,r as m,b,d as f,f as s,g as u,i as g,j as h,S as n,B as r,p as v,k}from"./index-e86ed1bf.js";import{_ as y}from"./4-2a4bab2f.js";const c=t=>(v("data-v-dfca525f"),t=t(),k(),t),x={class:"vh-100 pt-5",style:{"background-color":"#E8E1D4"}},w={class:"container py-5 h-100"},B={class:"row d-flex justify-content-center align-items-center h-100"},S={class:"col-lg-8"},I={class:"card",style:{"border-radius":"1rem"}},E={class:"row g-0"},T=c(()=>s("div",{class:"col-md-6 col-lg-5 d-none d-md-block"},[s("img",{src:y,alt:"login form",class:"img-fluid",style:{"border-radius":"1rem 0 0 1rem"}})],-1)),$={class:"col-md-6 col-lg-7 d-flex align-items-center"},j={class:"card-body p-4 p-lg-5 text-black"},C=c(()=>s("h2",{class:"pb-5",style:{"letter-spacing":"1px"}},"忘記密碼",-1)),D={class:"mb-4"},V=c(()=>s("label",{class:"form-label"},"請輸入信箱",-1)),M={class:"pt-1 mb-4"},N={__name:"forgotpassword",setup(t){const i="http://localhost:8080/fithub",e=m(""),d=async()=>{try{const a=await h.post(`${i}/members/forgotpassword/${e.value}`);n.fire({title:"請至信箱收取密碼重設信",icon:"success",confirmButtonText:"確定"}),r.push({name:"login"})}catch{n.fire({title:"查無此信箱",icon:"warning",confirmButtonText:"確定"})}},_=()=>{r.back()};return(a,o)=>(b(),f("section",x,[s("div",w,[s("div",B,[s("div",S,[s("div",I,[s("div",E,[T,s("div",$,[s("div",j,[C,s("div",D,[V,u(s("input",{type:"email",class:"form-control form-control-lg","onUpdate:modelValue":o[0]||(o[0]=l=>e.value=l),placeholder:"abc123456@example.com"},null,512),[[g,e.value]])]),s("div",M,[s("button",{class:"btn btn-dark btn-lg btn-block",type:"button",onClick:_},"返回"),s("button",{class:"btn btn-dark btn-lg btn-block ms-1",type:"button",onClick:o[1]||(o[1]=l=>d())},"確認")])])])])])])])])]))}},z=p(N,[["__scopeId","data-v-dfca525f"]]);export{z as default};
