function htmlAjax(nombreArchivo, idhtml){
    
    const request= new XMLHttpRequest();
    const url='./html-menu/'+nombreArchivo;

    request.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            
            document.getElementById(idhtml).innerHTML=this.responseText;
              
        }
    }

    request.open("GET", url);
    request.send();

}