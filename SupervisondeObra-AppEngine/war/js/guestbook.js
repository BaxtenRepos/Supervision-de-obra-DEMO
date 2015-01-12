/**
 * 
 */
function init() {
  window.init();
}
init= function() {
	gapi.client.guestbook.messages. insert(3).execute();
	};
	

		