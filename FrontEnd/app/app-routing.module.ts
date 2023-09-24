import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PaidBillComponent } from './paid-bill/paid-bill.component';
import { UnpaidComponent } from './unpaid/unpaid.component';
import { TransactionComponent } from './transaction/transaction.component';
import { PaymentComponent } from './payment/payment.component';

const routes: Routes = [
  {
    path:"",pathMatch:"full",redirectTo:"/login"
  },
  {
    path:"login" ,component:LoginComponent
  },
  {
    path:"home",component:HomeComponent
  },
  {
    path:"paid-bill", component:PaidBillComponent
  },
  {
    path:"unpaid-bill",component:UnpaidComponent
  },
  {
    path:"transaction", component:TransactionComponent
  },
  {
    path:'pay',component:PaymentComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
