function add(id){
    if(window.confirm('确认通过改申请吗？')){
//        alert("===:"+id);
    	return true;
     }else{
        //alert("取消");
        return false;
    }
 }
function del(){
    if(window.confirm('确认删除改申请吗？')){
        //alert("确定");
        return true;
     }else{
        //alert("取消");
        return false;
    }
 }  