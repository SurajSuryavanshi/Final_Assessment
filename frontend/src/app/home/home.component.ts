import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { otp } from '../model/otp';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  obj:any=<otp>{};
  constructor(private service:HttpService,private router:Router) { }

  ngOnInit(): void {
    this.loaddata();
  }

  onClick(){
    this.router.navigate(['/paid-bill'])
  }
  onClick1(){
    this.router.navigate(['/unpaid-bill'])
  }
  onClick2(){
    this.router.navigate(['/transaction'])
  }
  loaddata(){
    let customerid=sessionStorage.getItem("customerId");
    console.log(customerid);
    this.service.getOTP(customerid).subscribe((response)=>{
      this.obj=response;
  })

  }
  logout(){
    sessionStorage.removeItem("customerId");
    this.router.navigate(['/login']);
  }


}
