import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PaidBillComponent } from './paid-bill/paid-bill.component';
import { UnpaidComponent } from './unpaid/unpaid.component';
import { TransactionComponent } from './transaction/transaction.component';
import { PaymentComponent } from './payment/payment.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { AuthGuard } from './auth.guard';
import { compileClassMetadata } from '@angular/compiler';

const routes: Routes = [
  {
    path:"",pathMatch:"full",redirectTo:"/login"
  },
  {
    path:"login" ,component:LoginComponent
  },
  {
    path:"home",component:HomeComponent,canActivate:[AuthGuard]
  },
  {
    path:"paid-bill", component:PaidBillComponent,canActivate:[AuthGuard]
  },
  {
    path:"unpaid-bill",component:UnpaidComponent,canActivate:[AuthGuard]
  },
  {
    path:"transaction", component:TransactionComponent,canActivate:[AuthGuard]
  },
  {
    path:'pay/:id',component:InvoiceComponent,canActivate:[AuthGuard]
  },
  {
  path:'paynow/:id',component:PaymentComponent,canActivate:[AuthGuard]
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
