package com.etx.mortgagecalculator.client.ui.widgets;



import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.google.gwt.event.logical.shared.ValueChangeEvent;


public class uiwidget extends Composite implements HasText {
	

	private static uiwidgetUiBinder uiBinder = GWT
			.create(uiwidgetUiBinder.class);
	@UiField Label lblMortgageLabel;
	@UiField SimplePanel chartpanel;

	@UiField ListBox paymentfrequencyCombobox;
	
	@UiField TextBox mortgageamountTextbox;
	@UiField TextBox terminyearsTextbox;
	@UiField TextBox interestrateTextbox;	
	@UiField TextBox onetimeprepaymentTextbox;
	@UiField TextBox annualprepaymentTextbox;
	@UiField TextBox increasepaymentbyTextbox;
	@UiField LayoutPanel mainpanel;
	@UiField Label monthlyPayments;
	@UiField Label terminyears;
	@UiField Label lblpaymentlabel;
	@UiField Label totalcostoftheload;
	@UiField Label interestpaidfortheterm;
	@UiField DateBox firstdate;
	@UiField DateBox payoffdate;
	@UiField HTMLPanel htmlpanel;
	@UiField InlineHTML htmlinline;
	@UiField Label lblamortlabel;
	@UiField Label lblmortgageamortization;
	@UiField Label lblannualinterestrate;
	@UiField Label lblpaymentfrequency;
	@UiField Label lblpayoffdate;
	@UiField Label lblonetimeprepayment;
	@UiField Label lblannualprepayment;
	@UiField Label lblincreasepaymentby;
	@UiField Label lblcopyright;
	@UiField Label lbltotalcostoftheloan;
	@UiField Label lblinterestpaidforterm;
	@UiField Label lblextrapaymentssaved;
	@UiField Button button;
	@UiField Label inyears;
	@UiField Label extrasaved;
	
	 // Declare Variables and Values
	   private double principalBalance;       // Amount of Loan
	   private double loanTerm;               // Loan Term
	   private double interestRate;           // Interest Rate of Loan
	   private double interestMonthly;        // Amount of Monthly Interest
	   private double monthlyPayment;         // Monthly Payment Amount
	  // DecimalFormat Dollar;
private static ColumnChart lineChart;
NumberFormat fmt ;
int numyears;
String years="years";
String days="days";
String amortizationtable1lang = "";

String lang;
	
		interface uiwidgetUiBinder extends UiBinder<Widget, uiwidget> {
	}

	public uiwidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		Window.enableScrolling(false);
		Window.setMargin("0px");
  Date d = new Date();firstdate.setValue(d);

	 fmt = NumberFormat.getCurrencyFormat();
	 
		mortgageamountTextbox.addStyleName("gwt-mortgageamountTextbox");
		terminyearsTextbox.addStyleName("gwt-Textbox");
		interestrateTextbox.addStyleName("gwt-Textbox");
		onetimeprepaymentTextbox.addStyleName("gwt-Textbox");
		annualprepaymentTextbox.addStyleName("gwt-Textbox");
		increasepaymentbyTextbox.addStyleName("gwt-Textbox");
		htmlpanel.addStyleName("gwt-htmlpanel");
		monthlyPayments.addStyleName("gwt-monthlypayment");
		terminyears.addStyleName("gwt-terminyears");
		lblpaymentlabel.addStyleName("gwt-paymentlabel");
		payoffdate.addStyleName("gwt-PayoffDate");
		lblamortlabel.addStyleName("gwt-amortizationschedlabel");
		firstdate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("MMM dd, yyyy"))); 
		
		
calculatemortgage();click(button);
		
		button.setVisible(false);
		lblonetimeprepayment.setVisible(false);
		onetimeprepaymentTextbox.setVisible(false);
		lblannualprepayment.setVisible(false);
		annualprepaymentTextbox.setVisible(false);
		firstdate	.setVisible(false);
try {

lang = com.google.gwt.user.client.Window.Location.getParameter("lang");

if (lang == null) {lang="en";}

//Slovak
if (lang.equals("sk")==true) {
lblMortgageLabel.setText ( "Vyska Hypoteky" ) ;
lblmortgageamortization.setText ( "Doba Splacania (mesiace)" ) ;
lblannualinterestrate.setText ( "Rocna urokova miera (%)" ) ;
lblpaymentfrequency.setText ( "Frekvencia Platby" ) ;
//lblmortgagestartdate.setText ( "Datum Zaciatku Hypoteky" ) ;
lblpaymentlabel.setText ( "Mesacna Splatka:" ) ;
lblpayoffdate.setText ( "Datum Splatenia:" ) ;
lblonetimeprepayment.setText ( "Jednorazovo pridat" ) ;
lblannualprepayment.setText ( "Rocne pridat" ) ;
lblincreasepaymentby.setText ( "Mesacne Pridat" ) ;
lbltotalcostoftheloan.setText ( " Celkova Suma Uveru:" ) ;
lblinterestpaidforterm.setText ( "Celkovy Urok:" ) ;
lblextrapaymentssaved.setText ( "Platby navyse usetrili:" ) ;
//lblextrapaymentnewdate.setText ( "Datum splatenia s platbami navyse:" ) ;
lblamortlabel.setText ( "Splatkovy Kalendar (Mesacna Amortizacna Tabulka)" ) ;
lblcopyright.setText ( " Copyright - ETX Software as, Vsetky Prava Vyhradene." ) ;
years = "rokov";
days = "dni";

amortizationtable1lang = "<table style=\"width:100%\">"
  + "<tr>"
  + "<td><b>Mesiac</b></td>"
  + "<td><b>Platba</b></td>"
  + "<td><b>Umor</b></td>"
  + "<td><b>Urok</b></td>"
  + "<td><b>Urok (sucet)</b></td>"
  + "<td><b>Zostatok</b></td>"
  + "</tr>";


}



//English
if (lang.equals("en")==true) {
lblMortgageLabel.setText("Mortgage Amount");
lblmortgageamortization.setText("Mortgage Amortization (months)");
lblannualinterestrate.setText("Annual Interest Rate (%)");
lblpaymentfrequency.setText("Payment Frequency");
//lblmortgagestartdate.setText("Mortgage start date");
lblpaymentlabel.setText("Monthly Payment:");
lblpayoffdate.setText("Pay-Off Date (from today's date):");
lblonetimeprepayment.setText("One-Time Prepayment");
lblannualprepayment.setText("Annual Prepayment");
lblincreasepaymentby.setText("Add to Mortgage (montly)");
lbltotalcostoftheloan.setText("Total cost of the loan:");
lblinterestpaidforterm.setText("Interest Paid for Term:");
lblextrapaymentssaved.setText("Extra Payments Saved:");
//lblextrapaymentnewdate.setText("Pay-Off Date with Extra Payments. In:");
lblamortlabel.setText("Amortization Schedule (monthly payments)");
lblcopyright.setText("Copyright - ETX Software Inc., All Rights Reserved.");
years = "years";
days = "days";

amortizationtable1lang = "<table style=\"width:100%\">"
  + "<tr>"
  + "<td><b>Month</b></td>"
  + "<td><b>Payment</b></td>"
  + "<td><b>Principal Paid</b></td>"
  + "<td><b>Interest Paid</b></td>"
  + "<td><b>Total Interest</b></td>"
  + "<td><b>Balance</b></td>"
  + "</tr>";


}
} catch (Exception e) {
}

		
		
	}

 @Override public String getText() {
 // TODO Auto-generated method stub
 return null;
 }

 @Override public void setText(String text) {
 // TODO Auto-generated method stub
 
 }



private Widget getLineChart() {
if (lineChart == null) {
        lineChart = new ColumnChart();
}
return lineChart;
}



private void calculatemortgage() {



//	 Dollar = new DecimalFormat("$###,###.00");
	// Assign Amount of Loan
	double loanAmt = Double.parseDouble(mortgageamountTextbox.getText());
	AssignLoanAmount(loanAmt);

	// Assign Rate of Loan
	double rate = Double.parseDouble(interestrateTextbox.getText());
	AssignLoanRate(rate);

	// Assign Loan Term
	double term = Double.parseDouble(terminyearsTextbox.getText());
	//double term = Double.parseDouble(terminyearsTextbox.getText()) * 12;
	AssignLoanTerm(term);

	// Calculate Monthly Payment of Loan
	CalculateMonthlyPayment(); 
	UpdateCalculation();
	
//	monthlyPayments.setText("$"+String.valueOf((double)Math.round(monthlyPayment * 100) / 100));
	
	//result.setText("Loan Amount = " + Dollar.format(principalBalance)+ "\nInterest Rate = " + interestRate + "%" + "\nLength of Loan = "+ Math.round(loanTerm) + " months" + "\nMonthly Payment = "	+ Dollar.format(monthlyPayment));
	terminyears.setText(String.valueOf((double)Math.round((loanTerm/12) * 100) / 100) + " "+years);
	
	double monpayment = (monthlyPayment * 100) / 100;
	
	//double interestpaid = Math.round(totalcost-loanAmt);
	
	//interestpaidfortheterm.setText(String.valueOf(interestpaid));
	

PrintAmortizationSchedule(mortgageamountTextbox.getText(),terminyearsTextbox.getText(),interestrateTextbox.getText(),increasepaymentbyTextbox.getText());

}

public void AssignLoanAmount(double loanAmount) {
	   principalBalance = loanAmount;
   }

public void AssignLoanRate(double interestAmount) {
        interestRate = interestAmount;
   }

public void AssignLoanTerm(double termAmount) {
	   loanTerm = termAmount;
	  }

public void CalculateMonthlyPayment() {
    //If APR > 0
       if (interestRate > 0) {
	  interestMonthly = interestRate / (100.00 * 12.00);
	  monthlyPayment = principalBalance *((interestMonthly *(Math.pow((1 + interestMonthly),loanTerm)))
       /(Math.pow((1 + interestMonthly),loanTerm) - 1));
       }
          else {
          //If APR = 0 (an interest-free loan) 
           monthlyPayment = principalBalance / loanTerm;
       }
 }


public void UpdateCalculation() {

if (Integer.parseInt(terminyearsTextbox.getText())>1) {
	if (paymentfrequencyCombobox.getSelectedIndex()==0) {lblpaymentlabel.setText(paymentfrequencyCombobox.getItemText(paymentfrequencyCombobox.getSelectedIndex())+" Payment:");
 String formatted = fmt.format((double)Math.round(monthlyPayment * 100) / 100).replace("US$", "");
	monthlyPayments.setText(formatted);
	}
	
	if (paymentfrequencyCombobox.getSelectedIndex()==1) {lblpaymentlabel.setText("Semi-Monthly Payment:");
	monthlyPayments.setText("$"+String.valueOf((double)Math.round( ((monthlyPayment*12)/24) * 100) / 100));
	}
	
	if (paymentfrequencyCombobox.getSelectedIndex()==2) {lblpaymentlabel.setText("Bi-Weekly Payment:");
	monthlyPayments.setText("$"+String.valueOf((double)Math.round( ((monthlyPayment*12)/(52/2)) * 100) / 100));
	}
	
	if (paymentfrequencyCombobox.getSelectedIndex()==3) {lblpaymentlabel.setText("Weekly Payment:");
	monthlyPayments.setText("$"+String.valueOf((double)Math.round( ((monthlyPayment*12)/52) * 100) / 100));
	}
	
	if (paymentfrequencyCombobox.getSelectedIndex()==4) {lblpaymentlabel.setText("Accelerated Bi-Weekly Payment:");
	monthlyPayments.setText("$"+String.valueOf((double)Math.round( (((monthlyPayment*13))/(52/2)) * 100) / 100));
	}
} else {
monthlyPayments.setText(mortgageamountTextbox.getText());
}
	
	//if (paymentfrequencyCombobox.getSelectedIndex()==5) {paymentlabel.setText("Accelerated Weekly Payment:");}
	
}



@UiHandler("mortgageamountTextbox")	void onMortgageamountTextboxKeyUp(KeyUpEvent event) {
double mortgage = Double.parseDouble(mortgageamountTextbox.getText());
if (mortgage<1) {mortgageamountTextbox.setText("100");}
if (mortgage>=999999999) {mortgageamountTextbox.setText("999999999");}
calculatemortgage();click(button);
}
//@UiHandler("mortgageamountTextbox")	void onMortgageamountTextboxClick(ClickEvent event) {calculatemortgage();}

@UiHandler("terminyearsTextbox")	void onTerminyearsTextboxKeyUp(KeyUpEvent event) {
int length = Integer.parseInt(terminyearsTextbox.getText());
if (length>=480) {terminyearsTextbox.setText("480");
terminyears.setText(String.valueOf((double)Math.round((480/12) * 100) / 100) + " "+years);
} else {
terminyears.setText(String.valueOf((double)Math.round((loanTerm/12) * 100) / 100) + " "+years);
}

if (length<1) {terminyearsTextbox.setText("1");terminyears.setText(String.valueOf((double)Math.round((1/12) * 100) / 100) + " "+years);
}
calculatemortgage();click(button);
}

//@UiHandler("terminyearsTextbox")	void onTerminyearsTextboxClick(ClickEvent event) {calculatemortgage();terminyears.setText(String.valueOf((double)Math.round((loanTerm/12) * 100) / 100) + " years");}
@UiHandler("interestrateTextbox")	void onInterestrateTextboxKeyUp(KeyUpEvent event) {
double interest = Double.parseDouble(interestrateTextbox.getText());
if (interest<0.01) {interestrateTextbox.setText("0.01");}
if (interest>30.00) {interestrateTextbox.setText("30.00");}
calculatemortgage();click(button);
}
//@UiHandler("interestrateTextbox")	void onInterestrateTextboxClick(ClickEvent event) {calculatemortgage();}
	
	@UiHandler("paymentfrequencyCombobox") void onPaymentfrequencyComboboxChange(ChangeEvent event) {UpdateCalculation();}
	
	
	@UiHandler("increasepaymentbyTextbox")  void onIncreasepaymentbyTextboxKeyUp(KeyUpEvent event) { 
		calculatemortgage();click(button);
	}
	
 
	// make sure only numbers can be typed
	/*
	@UiHandler("mortgageamountTextbox")	void onMortgageamountTextboxKeyPress(KeyPressEvent event) {String bb = (mortgageamountTextbox.getText().replaceAll("[^\\d.]","")).substring(0,mortgageamountTextbox.getText().length()-1); mortgageamountTextbox.setText(bb.replaceAll("[^\\d.]",""));}
	@UiHandler("terminyearsTextbox")	void onTerminyearsTextboxKeyPress(KeyPressEvent event) {String bb1 = (terminyearsTextbox.getText().replaceAll("[^\\d.]","")).substring(0,terminyearsTextbox.getText().length()-1); terminyearsTextbox.setText(bb1.replaceAll("[^\\d.]",""));}
	@UiHandler("interestrateTextbox")	void onInterestrateTextboxKeyPress(KeyPressEvent event){String bb2 =(interestrateTextbox.getText().replaceAll("[^\\d.]","")).substring(0,interestrateTextbox.getText().length()-1);interestrateTextbox.setText(bb2.replaceAll("[^\\d.]",""));}
	*/
	
	
	public String UpdateCalculation(long seconds) 
	{
	numyears = (int) Math.floor(seconds / 31536000);
	int numdays = (int) Math.floor((seconds % 31536000) / 86400); 
	
	return  numyears + " "+years +  " " + numdays + " "+ days;//  + numhours + " hours " + numminutes + " minutes " + numseconds + " seconds";
	}
	
 public void PrintAmortizationSchedule(final String principalS, final String lengthS, final String interestS, final String increasepaymentbyS ) {

 
 
 ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
 chartLoader.loadApi(new Runnable() {
 String amortizationtable="";
 
         @Override
         public void run() {
         getLineChart().setPixelSize(700,300);
     //    layoutPanel = new SimpleLayoutPanel();
         chartpanel.setWidget(getLineChart());
                 
         
//       Prepare the data
         DataTable dataTable = DataTable.create();
         dataTable.addColumn(ColumnType.STRING, "Year");
         dataTable.addColumn(ColumnType.NUMBER, "Balance");
        
         //dataTable.addColumn(ColumnType.NUMBER, "Prepaid");


         ////System.out.println(Integer.parseInt(terminyearsTextbox.getText()));

         dataTable.addRows((Integer.parseInt(terminyearsTextbox.getText())/12)+2);
         
         for(int i=1; i<(Integer.parseInt(terminyearsTextbox.getText())/12)+1; i++){
         dataTable.setValue(i, 0, String.valueOf(i));
        }

         
         
         int y=1;
         
         
         
         double totalinterest = 0;
         double totalinterest2 = 0;
         double principal = Double.parseDouble(principalS);
         double principal1 = Double.parseDouble(principalS);
         
         double increasepaymentby = Double.parseDouble(increasepaymentbyS);
         
         
       //  dataTable.setValue(0, 1, principal );
         
         int length = Integer.parseInt(lengthS);
         double interest = Double.parseDouble(interestS);

         amortizationtable = amortizationtable1lang;
         
         if (interest<0.01) {interestrateTextbox.setText("0.01");interest=0.01;}
         if (length>480) {terminyearsTextbox.setText("480");length=480;}
         
         double monthlyInterest = interest / (12 * 100);
         final double monthlyPayment = principal * ( monthlyInterest / ( 1 - Math.pow((1 + monthlyInterest), (length*-1))))+increasepaymentby;
         final double monthlyPayment2 = principal * ( monthlyInterest / ( 1 - Math.pow((1 + monthlyInterest), (length*-1))));
         int totalmonths = 0;
         double lastprincipal = 0.00;
         double lasttotalinterest = 0.00;
         
         //System.out.println();
         for (int x = 1; x <= length; x++) {

         double amountInterest = principal * monthlyInterest;
         double amountPrincipal = monthlyPayment - amountInterest;
         totalinterest2= totalinterest2 + amountInterest;
         
         if (fmt.format(principal).contains("(")==false) {
         totalmonths=x;
         totalinterest = totalinterest + amountInterest;
         
         principal = principal - amountPrincipal;

         // //System.out.println("Month"+ "\t\t" +"Payment" + "\t" + "Principal Paid"+ "\t" +  "Interest Paid"+ "\t" + "Total Interest"+ "\t" + "Balance");
         
         //amortizationtable = amortizationtable+ (x+ "\t\t\t" +  fmt.format(monthlyPayment)+ "\t" +  fmt.format(amountPrincipal)+ "\t" +  fmt.format(amountInterest)+ "\t" +  fmt.format(totalinterest)+ "\t" +  fmt.format(principal)+"<br>");
         
         if (fmt.format(principal).contains("(")==false) {
         amortizationtable = amortizationtable+ ("<tr><td>"+x+"</td><td>" +  fmt.format(monthlyPayment).replace("US$", "")+"</td><td>" +  fmt.format(amountPrincipal).replace("US$", "")+ "</td><td>" +  fmt.format(amountInterest).replace("US$", "")+"</td><td>" +  fmt.format(totalinterest).replace("US$", "")+ "</td><td>" +  fmt.format(principal).replace("US$", "")+"</td> </tr>");
         lastprincipal = principal;
         lasttotalinterest=totalinterest;
         }
         
       // //System.out.println(x+ "\t\t\t" +  fmt.format(monthlyPayment)+ "\t" +  fmt.format(amountPrincipal)+ "\t" +  fmt.format(amountInterest)+ "\t" +  fmt.format(totalinterest)+ "\t" +  fmt.format(principal));
      
    
         
         if (x==12* numyears){
         ////System.out.println(x + " - " + length);
         
         
         double value = 0.00;
         try {
         value = Double.parseDouble(fmt.format(principal).replace("US$", "").replace(",", ""));
         } catch (NumberFormatException e) {
         }
         
    //     //System.out.println(x + value);
         //if (value<=0.00) {value=principal;}
        
         dataTable.setValue((x/12), 1,  fmt.format(value).replace("US$", "").replace(",", "") );
         dataTable.setFormattedValue((x/12), 0, " Year:"+String.valueOf(x/12));
         dataTable.setFormattedValue((x/12), 1, fmt.format(value).replace("US",""));
         
         } else  if (y==12){
         try {
         double value = Double.parseDouble(fmt.format(principal).replace("US$", "").replace(",", ""));
         if (value<0) {value=0.00;}
         
        dataTable.setValue((x/12), 1, value );
        dataTable.setFormattedValue((x/12), 0, " Year:"+String.valueOf(x/12));
        dataTable.setFormattedValue((x/12), 1, fmt.format(value).replace("US",""));
        
         } catch (NumberFormatException e) {
       dataTable.setValue((x/12)+1, 1, 0 ); 
         }
         y=0;  
        }
         y=y+1;

        
         
      
         }
          
         }
         

         
         if (Integer.parseInt(increasepaymentbyTextbox.getText())>=0) {
         double interestlast = totalinterest-lasttotalinterest;
         if (interestlast>0.0){
         amortizationtable = amortizationtable+ ("<tr><td>"+totalmonths+"</td><td>" +  fmt.format(lastprincipal+interestlast).replace("US$", "")+"</td><td>" +  fmt.format((lastprincipal+interestlast)-interestlast).replace("US$", "")+ "</td><td>" +  fmt.format(interestlast).replace("US$", "")+"</td><td>" +  fmt.format(interestlast+lasttotalinterest).replace("US$", "")+ "</td><td>0.00</td> </tr>");
         }
         } else {
         double interestlast = monthlyPayment-lastprincipal;
         //System.out.println(monthlyPayment);
         //System.out.println(lastprincipal);
         //System.out.println(interestlast);
         if (interestlast>0.0){
         amortizationtable = amortizationtable+ ("<tr><td>"+totalmonths+"</td><td>" +  fmt.format(monthlyPayment).replace("US$", "")+"</td><td>" +  fmt.format(monthlyPayment-interestlast).replace("US$", "")+ "</td><td>" +  fmt.format(interestlast).replace("US$", "")+"</td><td>" +  fmt.format(totalinterest+interestlast).replace("US$", "")+ "</td><td>0.00</td> </tr>");
         }}

    //     dataTable.setValue(countmonths-1, 1, 0 );
         
         interestpaidfortheterm.setText(fmt.format(totalinterest).replace("US$", ""));
         
         if (fmt.format(principal).contains("(")==true) {
         principal=0.00;
         }
         
         double totaloan = (principal1+totalinterest)+principal;
         totalcostoftheload.setText( fmt.format(totaloan).replace("US$", ""));
         double dlff = (monthlyPayment2*length) - totaloan;
         extrasaved.setText(fmt.format(dlff).replace("US$", "").replace("(", "").replace(")", ""));
         
       
         
         amortizationtable = amortizationtable+ "</tr></table>";
         
         
         
         

         Date d = new Date();
       
        d.parse(firstdate.getValue().toString());
        
      //  System.out.println(d.toGMTString());

        double daystillpayoffdate =  (totalmonths*30.41666666666667);
        int daystillpayoffdateint= (int) daystillpayoffdate;

        //System.out.println(daystillpayoffdateint);
        d.setDate(d.getDate() + daystillpayoffdateint);

        payoffdate.setValue(d);
        payoffdate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("MMM, yyyy"))); 

        long difference = d.getTime() -  firstdate.getValue().getTime() ;
        inyears.setText(UpdateCalculation(difference/1000));
       // firstdate.setValue(d);
   //     firstdate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("MMM dd, yyyy"))); 
         
         
         
         htmlinline.setHTML(amortizationtable);
         htmlinline.setHeight("120px");

         //printAmortizationTable(2020.00,120,Double.parseDouble(mortgageamountTextbox.getText()),interestRate);
         //dataTable.setValue(2,1,0);
         //dataTable.setValue((Integer.parseInt(terminyearsTextbox.getText())/12), 1,0);
         /*
         dataTable.setValue(0, 2, 35000);
         dataTable.setValue(1, 2, 47000);
         dataTable.setValue(2, 2, 65000);
         dataTable.setValue(3, 2, 45000);
         */

       //  lineChart.clearChart();
         lineChart.draw(dataTable);
         }
 });

 
 }
 
 
 private void click(HasHandlers handlerSource) {
 NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false);
 DomEvent.fireNativeEvent(event, handlerSource);
}
 
 
 @UiHandler("button")
 void onButtonClick(ClickEvent event) {
 calculatemortgage();
 }
 @UiHandler("firstdate")
 void onFirstdateValueChange(ValueChangeEvent<?> event) {
 calculatemortgage();click(button);
 }
	}

