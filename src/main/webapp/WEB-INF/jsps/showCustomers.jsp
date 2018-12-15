<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  
   <script type="text/javascript">
   			 var erowobj="";
   			$(document).ready(function(){
   				
   				$('#tphoto').change(function() {
   	  				var file = this.files[0]; //reading the selected file
   	  				getBase64(file)
   	  				.then(function (data) {
   	  					// data = base64 encoded image 
   	  					 $('#photo').val(data);
   	  					//<img src="" id="review-photo"  style="height: 80px;">
   	  					$('#review-photo').attr('src', data);
   	  				}).catch(function (e) {
   	  					alert(e);
   	  				});
   	  			});
   				
   				$("#editConfirmBtn").click(function(){
					alert("@()@(@(@(@(@()))))"); 
					//Here we have to write code to send popup form data to the controller
					//AJAX only.
					var pdata = $('#cform').serialize();//name, email ,mobile,gender,city
					//here we are not sending json data , we are sending form data
					//name=Pocker&email=pocker%40gmail.com&gender=Male&mobile=0292929229&photo=&city=Oakland&id=
					//$.get ,$.post
					$.ajax({
						type:'POST',
						url: "${pageContext.request.contextPath}/edit-profile",
						data: pdata,
						dataType: 'json', //I want json response
						success: function(data){
							//below line will close the popup
							if(data.status=="success"){
								//Please write code to update the particular row
								erowobj.find('[tdname=name]').html($("#name").val());
								erowobj.find('[tdname=email]').html($("#email").val());
								erowobj.find('[tdname=gender]').html($("#gender").val());
								erowobj.find('[tdname=mobile]').html($("#mobile").val());
								erowobj.find('[tdname=city]').html($("#city").val());
								erowobj.find('[tdname=rowphoto]').attr("src",$("#photo").val());
								
								$("#editCustomerModel").modal("hide");	
								
							}else{
								alert("Sorry! data could not be updated!!!!!!!!!!!!!!");
							}
						},
						 error: function (textStatus, errorThrown) {
				                Success = false;//doesnt goes here
				         }
					});	
					console.log("pdata: " + pdata);
   				});
   			});	
   			
   			
   		//This will give you select file into base64 encoding
   	  		//file - file to be converted!
   			function getBase64(file) {
   				return new Promise(function (resolve, reject) {
   					const reader = new FileReader();
   					reader.readAsDataURL(file);
   					reader.onload = function() {
   						resolve(reader.result);
   					};
   					reader.onerror = function(error) {
   						reject(error);
   					};
   				});
   			}
   	
   			function deleteRow(email,name){
   				//http://localhost:444/spring-profile-mvc/jdeleteCustomer?email=nahshsh@gmail.com
   				$.get("${pageContext.request.contextPath}/jdeleteCustomer?email="+email,function(result){
					//printing the json response
					console.log(result);
					if(result.staus="deleted"){
						$("#"+name).hide();
					}
				});		
   			}
   			
   			function showEditModal(email,name){
   				//below line gives you reference row object to be edited
   				var customerRow=$("#"+name);
   				//storing reference of current row inside a globle variable!@
   				erowobj=customerRow;
   				var tname=customerRow.find('[tdname=name]').html();
   				var temail=customerRow.find('[tdname=email]').html();
   				var tmobile=customerRow.find('[tdname=mobile]').html();
   				var tgender=customerRow.find('[tdname=gender]').html();
   				var tcity=customerRow.find('[tdname=city]').html();
   				//
   				var imageValue=customerRow.find('[tdname=rowphoto]').attr("src");
   				
   				//setting name field inside the modal name
   				$("#name").val(tname);
   				$("#email").val(temail);
   				$("#mobile").val(tmobile);
   				$("#gender").val(tgender);
   				$("#city").val(tcity);
   				$("#review-photo").attr("src",imageValue);//<img id="review-photo" src="ahah.jpg"> 
   				$("#editCustomerModel").modal("show");		
   			}
   </script>
  
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  
  <form action="${pageContext.request.contextPath}/save-review-profile" method="post">
  
  <p>User Profile Review Page</p>            
  <table class="table table-bordered">
    <tbody>
      <tr style="background-color: fuchsia;">
        <td>Name</td>
        <td>Email</td>
         <td>Mobile</td>
          <td>Gender</td>
           <td>City</td>
            <td>Photo</td>
      </tr>
      <c:forEach items="${customers}" var="customer">
       <tr id="${customer.name}">
        <td tdname="name">${customer.name}</td>
         <td tdname="email">${customer.email}</td>
        <td tdname="mobile">${customer.mobile}</td>
         <td tdname="gender">${customer.gender}</td>
       <td tdname="city">${customer.city}</td>
       <td>
	       <img tdname="rowphoto" id="${customer.name}_imageicon" src="${customer.photo}" style="height: 100px;"/>
	        <a href="javascript:deleteRow('${customer.email}','${customer.name}');">
	        <img  src="${pageContext.request.contextPath}/images/delete.png"></a>
	        /
	        <a href="javascript:showEditModal('${customer.email}','${customer.name}');">
	        <img src="${pageContext.request.contextPath}/images/edit.png"></a>
       </td>
      </tr>
      </c:forEach>
      
      
      
        <tr>
        <td>
        <a href="${pageContext.request.contextPath}/add-profile">
        <button type="button" class="btn btn-danger btn-lg">Back!</button>
        </a>
        </td>
        <td align="right"></td>
        <td>&nbsp;</td>
          <td>&nbsp;</td>
            <td>&nbsp;</td>
              <td>&nbsp;</td>
      </tr>
      
    </tbody>
  </table>
  </form>
</div>

<form id="cform"  style="padding-left: 2rem; padding-right: 2rem;">
<div id="editCustomerModel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit Customer</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
						<div class="form-group">
							<label for="name">Name:</label> 
							<input type="text"
								class="form-control" id="name" placeholder="Enter Name"
								name="name" value="">
						</div>
						<div class="form-group">
							<label for="email">Email:</label> 
							<input type="email"
								class="form-control" id="email" placeholder="Enter Email"
								name="email" value="" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="gender">Gender:</label> <br>
							  <select name="gender" id="gender" class="form-control">
							  		<option>Male</option>
							  		<option>Female</option>
							  
							  </select>	
						</div>
						<div class="form-group">
							<label for="mobile">Mobile:</label> 
							<input type="tel"
								class="form-control" id="mobile"
								placeholder="Enter Mobile Number" name="mobile"
								value="">
						</div>
						<div class="form-group">
							<label for="photo">Photo:</label> <br> 
							<img
								id="review-photo" alt="customer photo"
								src=""
								style="height: 100px;"> <br>
								
								 <input type="file"
								class="form-control-file" id="tphoto" accept="image/*"> 
								
								<input
								id="photo" name="photo" type="hidden"
								value="">
						</div>
						<div class="form-group">
							<label for="address">City:</label> <select
								class="custom-select mr-sm-2" id="city" name="city">
								<option disabled>Choose...</option>

								<c:forEach items="${cities}" var="city">
									<option value="${city}">${city}</option>
								</c:forEach>
							</select>
						</div>

						<input type="hidden" id="id" name="id" value="">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="editConfirmBtn" type="button" class="btn btn-success">Update</button>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>
