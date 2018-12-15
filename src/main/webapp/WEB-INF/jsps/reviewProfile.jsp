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
</head>
<body>

<div class="container">
  <h2>Learning Spring 5.x MVC!</h2>
  <img src="${pageContext.request.contextPath}/images/1.jpg" style="height: 130px;">
  
  <form action="${pageContext.request.contextPath}/save-review-profile" method="post">
  
  <p>User Profile Review Page</p>            
  <table class="table table-bordered">
    <tbody>
      <tr>
        <td>Name</td>
        <td>${profile.name}
        
         <input type="hidden" value="${profile.name}" name="name"/>
        </td>
      </tr>
       <tr>
        <td>Email</td>
         <td>${profile.email}
          <input type="hidden" value="${profile.email}" name="email"/>
         </td>
      </tr>
      <tr>
        <td>Gender</td>
         <td>${profile.gender}
           <input type="hidden" value="${profile.gender}" name="gender"/>
         </td>
      </tr>
      <tr>
        <td>Mobile</td>
         <td>${profile.mobile}
         
          <input type="hidden" value="${profile.mobile}" name="mobile"/>
         </td>
      </tr>
      
      <tr>
        <td>City</td>
        <td>${profile.city}
        
         <input type="hidden" value="${profile.city}" name="city"/>
        </td>
      </tr>
      
      
      
         <tr>
        <td>Photo</td>
        <td> 
        
        
        <img src="${profile.photo}" style="height: 130px;">
        
         <input type="hidden" value="${profile.photo}" name="photo"/>
         
        </td>
      </tr>
      
        <tr>
        <td><button type="button" class="btn btn-danger btn-lg">Back!</button></td>
        <td align="right"><button type="submit" class="btn btn-primary btn-lg">Save!</button>
        
        <a href="${pageContext.request.contextPath}/">
        <button type="button" class="btn btn-primary btn-lg">Show Customers!</button>
        
        </a></td>
      </tr>
      
    </tbody>
  </table>
  </form>
</div>


</body>
</html>
