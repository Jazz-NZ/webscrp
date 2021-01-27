
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  width: 200px;
  height: 100px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
</head>
<body>

<form action="/selected">
<div class="dropdown">
  <button class="dropbtn">Matematika</button>
  <div class="dropdown-content">
        
    
  	<input type="checkbox" " name="mata1" value="matematika1">
		<label for="vehicle1"> Matematika 1 </label><br>
                             
	<input type="checkbox"  name="mata2" value="matematika2">
		<label for="vehicle1"> Matematika 2 </label><br>
                             
	<input type="checkbox"  		name="mata3" value="matematika3">
		<label for="vehicle1"> Matematika 3 </label><br>
	
	<input type="checkbox"  		name="dms" value="dms">
		<label for="vehicle1">  Diskretne matematicke strukture </label><br>
	
	<input type="checkbox"  		name="num" value="numericka-analiza">
		<label for="vehicle1"> Numericka analiza </label><br>
	
	<input type="checkbox"  		name="ela" value="elementi-teorije-algoritama">
		<label for="vehicle1"> Elementi teorije algoritama</label><br>	
	
	<input type="checkbox"  		name="mim" value="matematika-muzika">
		<label for="vehicle1"> Matematika i muzika</label><br>
	
	<input type="checkbox"  		name="mlip" value="matematicka-logika">
		<label for="vehicle1"> Matematicka logika i primene </label><br>
	
	<input type="checkbox"  		name="msp" value="softverski-paketi">
		<label for="vehicle1"> Matematicki softverski paketi </label><br>
	
	<input type="checkbox"  		name="okg" value="osnovi-kompjuterske-geometrije">
		<label for="vehicle1"> Osnovi kompjuterske geometrije </label><br>
	
	<input type="checkbox"  		name="ump" value="matematicko-programiranje">
		<label for="vehicle1"> Uvod u matematicko programiranje </label><br>	
		
	
    
    
  </div>
</div>

                             <p> </p>
                             <p> <input type="submit" value="Submit"> </p> 
                                                                    </form>
                                                                    
</body>
</html>
