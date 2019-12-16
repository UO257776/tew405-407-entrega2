
function Access(url) {
	if(typeof(Storage) !== "undefined") {
		var user;
		var password;
		user = sessionStorage.getItem("user");
		password = sessionStorage.getItem("passwd");
		var param_url = url + "?user=" + user + "&password=" + password;
		window.location.href = param_url;
	}
}