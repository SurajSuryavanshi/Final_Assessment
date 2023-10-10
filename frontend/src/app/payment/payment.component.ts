import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../http.service';
import { NgForm, NgModel } from '@angular/forms';
import { receipt } from '../model/receipt';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  invoicedata:any=<receipt>{};

  constructor(private router:Router ,private service:HttpService,private route:ActivatedRoute ) {
    
   }

  ngOnInit(): void {
  }

  goback(){
    this.router.navigate(['/unpaid-bill'])
  }

  pay(f:NgForm ){
    this.route.paramMap.subscribe((pragma)=>{
      this.service.getinvoiceData(pragma.get("id")).subscribe((response)=>{
        this.invoicedata=response;
        this.service.addpaymentdetails(this.invoicedata.invoiceId)
    .subscribe((response:any)=>{
      alert("Paid Successfully");
      this.router.navigate(['/paid-bill'])
      })
    })    
    },(error)=>{
      alert("Faild");
    })
    // this.router.navigate(['/paid-bill'])
    // alert("Paid Successfully")
    

  } 


}
