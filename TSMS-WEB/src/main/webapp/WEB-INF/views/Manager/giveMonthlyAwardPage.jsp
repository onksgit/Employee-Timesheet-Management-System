<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href='<c:url value="/resources/webtools/css/select2.min.css"></c:url>'>
<script type="text/javascript" src='<c:url value="/resources/webtools/commonjs/select2.full.min.js"></c:url>'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

 <script>
 function selectAward(award) {
 	var checkboxes = document.querySelectorAll('.checkSelection');
//     var justification = document.querySelectorAll('.justification');
    var anyChecked = false;

    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {console.log(checkbox)
        	document.getElementById('justification'+checkbox.value).disabled = false;
        }
        else
        	{
        	document.getElementById('justification'+checkbox.value).disabled = true;


        	}
        	
    });
    
//     justification.forEach(function(justify) {
//         if (checkbox.checked) {
//         	justify.disabled = false;
//         }
//     });
    
//     justification.disabled = !anyChecked;
}
 
$(document).ready(function() {
	    const ratingInputs = document.querySelectorAll('.rating');
	    const ratingMessage = document.getElementById('ratingMessage');
	    const submitButton = document.getElementById('submitButton');

	    ratingInputs.forEach(input => {
	      input.addEventListener('change', () => {
	        const ratingValue = parseInt(input.value);
	        if (ratingValue >= 1 && ratingValue <= 3) {
	          ratingMessage.textContent = 'Below Expected';
	        } else if (ratingValue >= 4 && ratingValue <= 7) {
	          ratingMessage.textContent = 'Expected';
	        } else if (ratingValue >= 8 && ratingValue <= 10) {
	          ratingMessage.textContent = 'More Than Expected';
	        }
	        submitButton.style.display = 'block';
	    });
	});
});
 </script>

<div class="row">
	<div class="col-lg-12" align="center">
		<h4 class="title">Award Nominations</h4>
	</div>
</div>
<% String empCode = request.getParameter("empCode"); %>

<form:form action="AddAward.html" class="form-horizontal" role="form"
	modelAttribute="awardRequest" name="" validate="" method="POST"
	onsubmit="return validation()">
	<form:hidden path="empCode"  id="empcode" value="${empCode}"/>
	<br>
	<div class="row">
<div class="col-sm-12" align="center">
<!--   <label class="control-label" style="padding-left: 20px;">Rating</label>  -->
<!--            &nbsp; <button type="button" class="btn" data-toggle="modal" data-target="#exampleModal"> -->
<!--     		<i class="fas fa-info"></i></button> -->
 
<!--   <div class="rating"> -->
<!--     <input type="radio" id="star10" name="rating" value="10"><label for="star10" title="10 stars">★</label> -->
<!--     <input type="radio" id="star9" name="rating" value="9"><label for="star9" title="9 stars">★</label> -->
<!--     <input type="radio" id="star8" name="rating" value="8"><label for="star8" title="8 stars">★</label> -->
<!--     <input type="radio" id="star7" name="rating" value="7"><label for="star7" title="7 stars">★</label> -->
<!--     <input type="radio" id="star6" name="rating" value="6"><label for="star6" title="6 stars">★</label> -->
<!--     <input type="radio" id="star5" name="rating" value="5"><label for="star5" title="5 stars">★</label> -->
<!--     <input type="radio" id="star4" name="rating" value="4"><label for="star4" title="4 stars">★</label> -->
<!--     <input type="radio" id="star3" name="rating" value="3"><label for="star3" title="3 stars">★</label> -->
<!--     <input type="radio" id="star2" name="rating" value="2"><label for="star2" title="2 stars">★</label> -->
<!--     <input type="radio" id="star1" name="rating" value="1"><label for="star1" title="1 star">★</label> -->
<!--   </div> -->

			
			<div id="remark">
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">Rating
						<span style="color: red;">*</span>
					</label>
					<div class="col-sm-2">
						<select id="star-rating" name="rating" class="form-control rating">
							<option value="10">10 Marks</option>
							<option value="9">9 Marks</option>
							<option value="8">8 Marks</option>
							<option value="7">7 Marks</option>
							<option value="6">6 Marks</option>
							<option value="5">5 Marks</option>
							<option value="4">4 Marks</option>
							<option value="3">3 Marks</option>
							<option value="2">2 Marks</option>
							<option value="1" selected="selected">1 Marks</option>
						</select>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn" data-toggle="modal"
							data-target="#exampleModal">
							<i class="glyphicon glyphicon-info-sign"></i>
						</button>
					</div>
				</div>
				<div class="message" id="ratingMessage"></div><br>
		</div>
			
			
			<div id="remark">
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">Remark
						<span style="color: red;">*</span>
					</label>
					<div class="col-sm-3">
						<form:input type="text" path="remark" id="remark"
							autocomplete="off" class="form-control" value=""
							required="required" placeholder="Remark" />
						<span id="remarkerror" style="color: red;"></span>
					</div>
				</div>
			</div>
			<hr>
  </div>
</div><br>
		<div class="row">
			<div class="col-lg-1" align="left"></div>
			<div class="col-lg-10">
				<div class="col-lg-12">
					<div class="col-lg-5 well well-lg">
						<div class="col-lg-12">
							<div class="table-responsive">
								<div class="cards-container">
									<div class="card"  data-id="1">
											<form:checkbox path="awardId" id="isawardSelected"
											class="checkSelection check2" value="1" onchange="selectAward(this)"/>
										<img src='<c:url value="/resources/images/award.png"></c:url>'
											style="width: 50px" alt="Award Image" class="profile-image">
										<div class="card-content">
	<%-- 										<form:hidden path="awardId" value="1"/> --%>
											<h4>Goes The Extra Mile Award</h4>
											<h5>This award Recognises employee who consistently
												demonstrates a willingness to go above & beyond their regular
												job duties or expectations.</h5>
										</div>
										<label class="control-label col-sm-5">Justification:<span style="color: red;">*</span></label>
										<div class="col-sm-7">
											<form:input type="text" path="justification" id="justification1" required="required"
												disabled="true" autocomplete="off" class="form-control" value=""
												placeholder="(Required if Award is selected)" />
											<span id="justificationerror" style="color: red;"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-lg-2"></div>
					<div class="col-lg-5 well well-lg">
						<div class="col-lg-12">
							<div class="table-responsive">
								<div class="cards-container">
									<div class="card"  data-id="2" >
									<form:checkbox path="awardId" id="isawardSelected" onchange="selectAward(this)"
											class="checkSelection check2" value="2"/>
										<img src='<c:url value="/resources/images/award.png"></c:url>'
											style="width: 50px" alt="Award Image" class="profile-image">
										<div class="card-content">
	<%-- 										<form:hidden path="awardId" value="2"/> --%>
											<h4>Most Improved</h4>
											<h5>This award celebrates employee who has shown
												significant improvement in their performance, skills or
												productivity.</h5>
										</div>
										<label class="control-label col-sm-5">Justification:<span style="color: red;">*</span></label>
										<div class="col-sm-7">
											<form:input type="text" path="justification" id="justification2" required="required"
												disabled="true" autocomplete="off" class="form-control" value=""
												placeholder="(Required if Award is selected)" />
											<span id="justificationerror" style="color: red;"></span>
										</div>										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-1" align="right"></div>
		</div>
	
		<div class="row">
			<div class="col-lg-1" align="left"></div>
			<div class="col-lg-10">
				<div class="col-lg-12">
					<div class="col-lg-5 well well-lg">
						<div class="col-lg-12">
							<div class="table-responsive">
								<div class="cards-container">
									<div class="card" data-id="3" >
									<form:checkbox path="awardId" id="isawardSelected" onchange="selectAward(this)"
											class="checkSelection check2" value="3"/>
										<img src='<c:url value="/resources/images/award.png"></c:url>'
											style="width: 50px" alt="Award Image" class="profile-image">
										<div class="card-content">
	<%-- 									<form:hidden path="awardId" value="3"/> --%>
											<h4>Out of the Box -Brainstormed Award</h4>
											<h5>This award recognises employee who has shown
												creativity and out of the box thinking in their work.</h5>
										</div>
										<label class="control-label col-sm-5">Justification:<span style="color: red;">*</span></label>
										<div class="col-sm-7">
											<form:input type="text" path="justification" id="justification3" required="required"
												disabled="true" autocomplete="off" class="form-control" value=""
												placeholder="(Required if Award is selected)" />
											<span id="justificationerror" style="color: red;"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
	
					<div class="col-lg-2"></div>
					<div class="col-lg-5 well well-lg">
						<div class="col-lg-12">
							<div class="table-responsive">
								<div class="cards-container">
									<div class="card" data-id="4" >
									<form:checkbox path="awardId" id="isawardSelected" onchange="selectAward(this)"
											class="checkSelection check2" value="4"/>
										<img src='<c:url value="/resources/images/award.png"></c:url>'
											style="width: 50px" alt="Award Image" class="profile-image">
										<div class="card-content">
	<%-- 									<form:hidden path="awardId" value="4"/> --%>
											<h4>Rising Star Award</h4>
											<h5>This award recognises new employees who have made a
												significant impact or shown exceptional potential during
												their first 6 months with the company</h5>
										</div>
										<label class="control-label col-sm-5">Justification:<span style="color: red;">*</span></label>
										<div class="col-sm-7">
											<form:input type="text" path="justification" id="justification4" required="required"
												disabled="true" autocomplete="off" class="form-control" value=""
												placeholder="(Required if Award is selected)" />
											<span id="justificationerror" style="color: red;"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
		<div class="col-lg-1" align="right"></div>
	</div>

<!-- 	<div id="awardjustification"> -->
<!-- 		<div class="form-group inputpd"> -->
<!-- 			<label class="control-label col-sm-5" style="padding-left: 20px;"> -->
<!-- 				Award Justification: </label> -->
<!-- 			<div class="col-sm-3"> -->
<%-- 				<form:input type="text" path="justification" id="justification" --%>
<%-- 					disabled="true" autocomplete="off" class="form-control" value="" --%>
<%-- 					placeholder="(Required if Award is selected)" /> --%>
<!-- 				<span id="justificationerror" style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<!-- 			<div id="remark"> -->
<!-- 			<div class="form-group inputpd"> -->
<!-- 			<label class="control-label col-sm-3" style="padding-left: 20px;">Remark -->
<!-- 				<span style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-3"> -->
<%-- 				<form:input type="text" path="remark" id="remark" --%>
<%-- 					autocomplete="off" class="form-control" value="" required="required" --%>
<%-- 					placeholder="Remark" /> --%>
<!-- 				<span id="remarkerror" style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		  </div> -->
		 
		  <div align="center" style="margin-bottom: 5%;">
	      <button type="submit" class="btn bg-black">Submit</button>
		  <a href="viewEmployeeMonthlyWorkForAwards.html?empCode=${empCode}"><button type="button" class="btn bg-black">Back</button></a>
	      </div>
</form:form>

 
 
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Rating Description</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>
				<h5>Below Expected:</h5> This stage indicates that the performance or outcome did not meet the expected standard. It's essential to provide constructive feedback and support to help team members understand where they fell short and how they can improve. This stage serves as an opportunity for learning and development, identifying areas for skill enhancement or process refinement.</p>
                <hr>
                <p>
                  <h5> Expected:</h5> This stage signifies that the performance or outcome met the anticipated standard. While it's a positive assessment, there may still be opportunities for refinement or optimization. Encouraging team members to reflect on their achievements within this stage can help identify strengths to build upon and areas where further improvement is possible. </p>
                <hr>
                <p>
                 <h5>More than Expected:</h5> This stage reflects performance or outcomes that surpassed expectations. It's important to acknowledge and celebrate achievements within this stage, recognizing the effort, innovation, or exceptional results achieved. Additionally, it's valuable to explore what contributed to the success and how it can be replicated or sustained in the future.                </p>
                <hr>
                <p>
				Now, to allocate a rating on a scale of 1 to 10 within these stages:
                </p>
                <ul>
                <li>Below Expected: 1 to 3</li>
                <li>Expected: 4 to 7</li>
				<li>More than Expected: 8 to 10 </li>
                </ul>
                <hr>
                <p>
				This breakdown allows for a nuanced evaluation of performance or outcome based on how it aligns with expectations. </p>
            </div>

        </div>
    </div>
</div>



<style>
  .rating {
    display: flex;
    flex-direction: row-reverse;
    justify-content: center;
  }

  .rating input {
    display: none;
  }

  .rating label {
    font-size: 3em; /* Default size for larger screens */
    color: #ddd;
    cursor: pointer;
    padding: 0 5px;
  }

  .rating input:checked ~ label {
    color: #ffca08;
  }

  .rating label:hover,
  .rating label:hover ~ label {
    color: #ffca08;
  }

  .message {
    text-align: center;
    margin-top: 20px;
    font-size: 1.2em;
  }

  .submit-button {
    display: none;
    text-align: center;
    margin-top: 20px;
  }

  .submit-button button {
    padding: 10px 20px;
    font-size: 1em;
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 5px;
  }

  /* Responsive styling */
  @media (max-width: 768px) {
    .rating label {
      font-size: 2em; /* Smaller size for tablets */
    }
  }

  @media (max-width: 480px) {
    .rating label {
      font-size: 1.5em; /* Even smaller size for mobile phones */
    }
  }
</style>




