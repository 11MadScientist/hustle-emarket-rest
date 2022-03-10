$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});




var search = document.getElementById("search");

function sellerList()
{
	var isProhibited = document.getElementById("prohibited").checked;

	var url = "?searchField=authorized&field=lastName&authorized=true&prohibited="
	+isProhibited+"&searchPattern="+document.getElementById("search-pattern").value;
	
	search.href += url;
}

function sellerRequests()
{
	var url = "&searchPattern="+document.getElementById("search-pattern").value;
	
	search.href += url;
}

function customerList()
{
	var isProhibited = document.getElementById("prohibited").checked;

	var url = "&prohibited="+isProhibited+"&searchPattern="+document.getElementById("search-pattern").value;
	
	search.href += url;
}

function storeList()
{
	var isProhibited = document.getElementById("prohibited").checked;

	var url = "&authorized=true&prohibited="+isProhibited+"&searchPattern="+document.getElementById("search-pattern").value;
	search.href += url;
}

function riderList()
{
	var isProhibited = document.getElementById("prohibited").checked;

	var url = "?searchField=authorized&field=lastName&authorized=true&prohibited="
	+isProhibited+"&searchPattern="+document.getElementById("search-pattern").value;
	
	search.href += url;
}

function riderRequest()
{
	var url = "&searchPattern="+document.getElementById("search-pattern").value;
	
	search.href += url;
}


function banAction()
{
	var prohibitIcon = document.getElementsByClassName("prohibit-icons");
	var isProhibited = document.getElementById("prohibited");
	
	
	
	if(isProhibited.checked == true)
	{
		var prohibitAction = document.getElementsByClassName("prohibit-action");
		for(var i = 0; i<prohibitAction.length; i++)
		{
			prohibitAction[i].className += " verify";
			prohibitAction[i].title = "enable";
			prohibitIcon[i].textContent = "done_all";
		}

	}
	else
	{
		var prohibitAction = document.getElementsByClassName("prohibit-action");
		for(var i = 0; i<prohibitAction.length; i++)
		{
			prohibitAction[i].className += " disable";
			prohibitAction[i].title = "disable";
			prohibitIcon[i].textContent = "block";
		}
		
	}
}
banAction();

