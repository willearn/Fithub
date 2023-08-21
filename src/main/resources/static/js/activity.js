 	 /*驗證活動,上下架日期*/
 	 function checkDate(event) {
		 var cancelsubmit = false;
			 
         const InputStartDate = document.getElementById("startdate");
         const InputEndDate = document.getElementById("enddate");
         const InputActivityDate = document.getElementById("activitydate");

         const startDate = new Date(InputStartDate.value);
         const endDate = new Date(InputEndDate.value);
         const activityDate = new Date(InputActivityDate.value);
         const currentDate = new Date();
         const fiveYearLaterDate = new Date(currentDate);
         fiveYearLaterDate.setFullYear(currentDate.getFullYear() + 5);

         if (startDate > fiveYearLaterDate) {
             alert("請輸入有效日期！");
             cancelsubmit = true;
         } else if (endDate > fiveYearLaterDate) {
             alert("請輸入有效日期！");
             cancelsubmit = true;
         } else if (activityDate > fiveYearLaterDate) {
             alert("請輸入有效日期！");
             cancelsubmit = true;
         }

         if (startDate > endDate) {
             alert('請確認上架/下架日期!');
             cancelsubmit =true;
         }else if(startDate > activityDate) {
             alert('請確認活動日期!');
             cancelsubmit = true;
         }else if(endDate < activityDate) {
             alert('請確認活動日期!');
             cancelsubmit = true;
         }

        	if (cancelsubmit == true) {
    		 event.preventDefault(); // 取消提交表单
 		  }
     }
     
     /*確認是否刪除並跳轉*/
     function deleteMessage() {
	    var result = confirm("是否確定要刪除?");
	
	    if (result) {
	        alert("已刪除");
	        return true; // 表單提交
	    } else {
	        alert("已取消");
	         return false; // 阻止表單提交
	    }
	}
	
