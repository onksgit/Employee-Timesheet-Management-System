
<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center">Add Timesheet</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>

<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form name="addtimesheet" method="POST" class="form-horizontal"
		 role="form"
		action="AddTimesheet"
		enctype="multipart/form-data" onsubmit="return validateForm()">

<!-- For Reference -> -->
<!-- 		<div class="form-group inputpd"> -->
<!-- 			<label class="control-label col-sm-5" style="padding-left: 20px;"> -->
<!-- 				Name<span style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-6"> -->
<!-- 				<input type="text" name="artname"  -->
<!-- 					autocomplete="off" id="ArtName" class="form-control" -->
<!-- 					placeholder="Enter Art Name" onblur="artWorkExist()" -->
<!-- 					onkeypress="return isValidInput(event)" /> -->
<!-- 				<span id="fileerror1" style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->
			<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Date:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<input type="date" name="addedTime" 
					autocomplete="off" id="addedTime" class="form-control"
					placeholder="Timesheed Date" onblur=""
					onkeypress="return isValidInput(event)"/>
				<span id="fileerror1" style="color: red;"></span>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Project Name:<span style="color: red;">*</span>
			</label>
			
			<div class="col-sm-6">
				<select type="" name="projectName" class="form-control" onkeypress="return isValidInput(event)">
				<option disabled="disabled">SELECT</option>
				<option id="abc">ABC</option>
				<option id="efg">EFG</option>
				<option id="hij">HIJ</option>
				<option id="klm">KLM</option>
				</select>
				<span id="fileerror1" style="color: red;"></span>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Reporting Manager:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<select type="" name="managerName" class="form-control" onkeypress="return isValidInput(event)">
				<option disabled="disabled">SELECT</option>
				<option id="abc" value="abc">ABC</option>
				<option id="efg"  value="efg">EFG</option>
				<option id="hij"  value="hij">HIJ</option>
				<option id="klm"  value="klm">KLM</option>
				</select>
				<span id="fileerror1" style="color: red;"></span>
			</div>
		</div>
			
			
<!-- 				<div class="col-sm-6"> -->
<!-- 				<input type="text" name="managerName"  -->
<!-- 					autocomplete="off" id="managerName" class="form-control" -->
<!-- 					placeholder="Reporting Manager" onblur="" -->
<!-- 					onkeypress="return isValidInput(event)"/> -->
<!-- 				<span id="fileerror1" style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Work Done:<span style="color: red;">*</span>
			</label>
				<div class="col-sm-6">
				<textarea name="workDone" 
					autocomplete="off" id="workDone" class="form-control"
					placeholder="Enter Total Work Done " onblur=""
					onkeypress="return isValidInput(event)"></textarea>
				<span id="fileerror1" style="color: red;"></span>
			</div>
		</div>
			
		

<!-- 		<div class="form-group inputpd"> -->
<!-- 			<label class="control-label col-sm-5" style="padding-left: 20px;">Size -->
<!-- 				(Ft)<span style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-3"> -->
<!-- 				<input type="text" name="width"  id="Width" -->
<!-- 					autocomplete="off" class="form-control" value="" -->
<!-- 					placeholder="Width" -->
<!-- 					onkeyup="if (/\D/g.test(this.value)) {this.value = this.value.replace(/\D/g,'');} calculate();" -->
<!-- 					onkeypress="fileerror3.innerHTML=''" onfocus="this.value=''" /> -->
<!-- 				<span id="fileerror3" style="color: red;"></span> -->
<!-- 			</div> -->

<!-- 			<div class="col-sm-3"> -->
<!-- 				<input type="text" name="height"  id="Height" -->
<!-- 					autocomplete="off" class="form-control" value="" -->
<!-- 					placeholder="Height" -->
<!-- 					onkeyup="if (/\D/g.test(this.value)){this.value = this.value.replace(/\D/g,'');} calculate();" -->
<!-- 					onkeypress="fileerror4.innerHTML=''" onfocus="this.value=''" /> -->
<!-- 				<span id="fileerror4" style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 		<div class="form-group inputpd"> -->
<!-- 			<label class="control-label col-sm-5" style="padding-left: 20px;">Photo<span -->
<!-- 				style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-6"> -->
<!-- 				<input type="file" name="file" class="form-control" accept="image/*" -->
<!-- 					onmouseup="fileerror2.innerHTML=''" /> <span id="fileerror2" -->
<!-- 					style="color: red;"></span> -->
<!-- 			</div> -->
<!-- 		</div> -->

		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<button class="btn bg-black" id="save" type="submit">Add 
			Timesheet</button>
			<button class="btn bg-black" type="button"
				onclick="javascript:goBack()">Back</button>
		</div>
	</form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>
