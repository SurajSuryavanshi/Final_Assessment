import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-paid-bill',
  templateUrl: './paid-bill.component.html',
  styleUrls: ['./paid-bill.component.css']
})
export class PaidBillComponent implements OnInit {
  paidData:any[]=[];

  
  constructor(private service:HttpService,private route:Router) { }


  ngOnInit(): void {
    this.GetPaidData();
  }

  GetPaidData(){
    let customerId1=sessionStorage.getItem("customerId");
    this.service.getPaidData(customerId1).subscribe((response:any)=>{
      this.paidData=response;
    })
  }
  pay(id:any){
    this.route.navigate(['/pay',id])
  }
  logout(){
    sessionStorage.removeItem("customerId");
    this.route.navigate(['/login']);
  }

}
