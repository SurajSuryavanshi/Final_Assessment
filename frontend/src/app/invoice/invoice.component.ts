import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute, Router } from '@angular/router';
import { otp } from '../model/otp';
import { receipt } from '../model/receipt';
import {jsPDF} from "jspdf";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
    @ViewChild('Card') contentToDownload!:ElementRef;
    customerData:any=<otp>{};
    invoicedata:any=<receipt>{};
    myDate = new Date();
  constructor(private service:HttpService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.getCustomerDetails();
  }
  paynow(){
    this.router.navigate(['/paynow',this.invoicedata.invoiceId])
  }

  getCustomerDetails(){
    let id=sessionStorage.getItem("customerId");
     this.service.getOTP(id).subscribe((response)=>{
      this.customerData=response; 
      this.route.paramMap.subscribe((pragma)=>{
        this.service.getinvoiceData(pragma.get('id')).subscribe((response)=>{
          this.invoicedata=response;
        })

      })

     })
  }

  downloadBill(){
    console.log("entered");
    let pdf=new jsPDF('p','pt','a2');
    pdf.html(this.contentToDownload.nativeElement,{
      callback:(pdf)=>{
        pdf.save("Bill.pdf");
      }
    })
  }




}
