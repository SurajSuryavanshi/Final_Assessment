import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { otp } from '../model/otp';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  obj:any=<otp>{};

  constructor(private service:HttpService,private router:Router) { }

  ngOnInit(): void {
  }
login(otp:any,customerID:string)
{
    let userotp=+otp; 
    let usercustomerid=+customerID;
    console.log(this.obj.otp.customerId) 
    if(userotp==this.obj.otp.otp && usercustomerid===this.obj.otp.customerId){
      sessionStorage.setItem("customerId",this.obj.otp.customerId);
      this.router.navigate(['/home']);
    }
    else{
      alert("invalid user")
    }
}
otp(id:any){
    let userid=+id;
    this.service.getOTP(userid).subscribe((response)=>{  
        this.obj=response;
        console.log(this.obj.otp.otp);
        alert(this.obj.otp.otp);
    },(error)=>{
      alert("user not present");
    })
  

}

}
