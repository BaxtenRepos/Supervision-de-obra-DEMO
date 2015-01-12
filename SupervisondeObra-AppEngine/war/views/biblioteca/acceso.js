

            
        
            function validaDato(){
                alert("Entrando a funci—n validaDato");
                var pass="1";
                var usu="yu";
                var usuario = document.getElementById('txtusername').value;
                var password = document.getElementById('txtpassword').value;
                //alert(document.getElementById('txtpassword').value);
                if(password == pass && usuario == usu){
                    // alert("Correcto");
                     crearBase();
                    
                   // init();
                   // document.forms[login-form].submit();
                } else{
                    alert("Datos incorrectos");
                }
            }
        
        
        