import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  transaction:any[]=[];
  constructor(private service:HttpService,private router:Router) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(){
    let customerid=sessionStorage.getItem("customerId");
    this.service.getTransactions(customerid).subscribe((response:any)=>{
        this.transaction=response;
        console.log(response);
    })
  }
  logout(){
    sessionStorage.removeItem("customerId");
    this.router.navigate(['/login']);
  }

}
