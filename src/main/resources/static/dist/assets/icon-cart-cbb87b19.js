import{x as u,b as r,q as p,y as o,e as a,z as b,t as i,_ as m,r as x,d as g,g as y,W as I,f as e,l as n,X as S,Y as C,E as $,R as f,p as h,k as v,P as N,s as k,c as T}from"./index-e86ed1bf.js";const w={__name:"simpleTag",props:{tagText:String},setup(t){const s=t;return(c,_)=>{const d=u("n-tag"),l=u("n-space");return r(),p(l,null,{default:o(()=>[a(d,{size:"large"},{default:o(()=>[b(i(s.tagText),1)]),_:1})]),_:1})}}};const R=t=>(h("data-v-95276b7d"),t=t(),v(),t),z={class:"card"},B=["src"],L={class:"card-body"},V={class:"card-text"},j={class:"d-flex justify-content-end"},A=R(()=>e("i",{type:"button",class:"bi bi-lightning-fill"},null,-1)),D={__name:"courseCard",props:{cardAmount:Number,course:Object,URL:String},setup(t){const s=x(!1),c=()=>{s.value=!0};return(_,d)=>(r(),g("div",z,[y(e("img",{src:`${t.URL}/course/getImg?cid=${t.course.courseId}`,class:"card-img-top mt-3",alt:"not Found",onLoad:c},null,40,B),[[I,s.value]]),s.value?$("",!0):(r(),p(n(C),{key:0,justify:"center",Align:"center"},{default:o(()=>[a(n(S),{size:"large",stroke:"#ffc408"})]),_:1})),e("div",L,[e("h5",V,i(t.course.courseName),1),a(w,{tagText:t.course.courseCategories.categoryName,class:"mb-3"},null,8,["tagText"]),e("div",j,[a(n(f),{to:`/course/detail/${t.course.courseId}`,class:"btn btn btn-primary"},{default:o(()=>[A,b(" 前往選課 ")]),_:1},8,["to"])])])]))}},X=m(D,[["__scopeId","data-v-95276b7d"]]);const E=t=>(h("data-v-93b33bb8"),t=t(),v(),t),F={class:"fixed-cart"},U={class:"btn btn-primary btn-circle position-relative"},q=E(()=>e("i",{type:"button",class:"bi bi-cart2",style:{"font-size":"30px"}},null,-1)),O={class:"position-absolute top-0 start-100 translate-middle badge rounded-pill bg-secondary"},P={__name:"icon-cart",setup(t){const s=N(),{courseCartStore:c}=k(s),_=T(()=>c.value.length);return(d,l)=>(r(),g("div",F,[a(n(f),{to:"/cart"},{default:o(()=>[e("button",U,[q,e("span",O,i(_.value),1)])]),_:1})]))}},Y=m(P,[["__scopeId","data-v-93b33bb8"]]);export{Y as C,X as c};
