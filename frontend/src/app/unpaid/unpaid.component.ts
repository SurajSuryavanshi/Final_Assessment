import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-unpaid',
  templateUrl: './unpaid.component.html',
  styleUrls: ['./unpaid.component.css']
})
export class UnpaidComponent implements OnInit {

  unpaidData:any[]=[];
  constructor(private service:HttpService,private router:Router) { }

  ngOnInit(): void {
    this.getUnPaidData();
  }

  getUnPaidData(){
    let customerId1=sessionStorage.getItem("customerId");
    this.service.getUnPaidData(customerId1).subscribe((response:any)=>{
      this.unpaidData=response;
    })
  }
  pay(id:any){
    this.router.navigate(['/pay',id])
  }
  logout(){
    sessionStorage.removeItem("customerId");
    this.router.navigate(['/login']);
  }

}
