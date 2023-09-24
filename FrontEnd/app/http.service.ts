import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  url:string="http://localhost:9595/"

  constructor(private http:HttpClient) { }


  getOTP(id:any){
    return (this.http.post(`${this.url}check-valid/${id}`,null))
  }

  getPaidData(customerId:any){
    let id=+customerId;
    return(this.http.post(`${this.url}get-paid/${id}`,null))
  }
  getUnPaidData(customerId:any){
    let id=+customerId;
    return(this.http.post(`${this.url}get-unpaid/${id}`,null))
  }
  getTransactions(customerId:any){
    let id=+customerId;
    return(this.http.post(`${this.url}get-history/${id}`,null));
  }


}
