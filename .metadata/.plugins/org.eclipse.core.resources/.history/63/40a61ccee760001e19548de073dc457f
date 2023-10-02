const homeButton = document.getElementById('homeButton');
const fileInput = document.getElementById('fileInput');
const uploadButton = document.getElementById('uploadButton');
const fileList = document.getElementById('fileList');

// Home button click event
homeButton.addEventListener('click', function() {
	// Redirect to the home page or perform any desired action
	window.location.href = 'home.html'; // Change this to your home page URL
});

// Upload button click event
uploadButton.addEventListener('click', function() {
	const selectedFile = fileInput.files[0];
	if (selectedFile) {
		// You can perform file upload logic here
		// In this example, we'll just display the uploaded file in the list
		const listItem = document.createElement('li');
		listItem.textContent = selectedFile.name;
		const viewButton = document.createElement('button');
		viewButton.textContent = 'View';
		viewButton.addEventListener('click', function() {
			// Implement view functionality based on file type
			const fileType = selectedFile.type;
			if (fileType === 'application/pdf') {
				window.open(URL.createObjectURL(selectedFile), '_blank');
			} else if (fileType === 'application/vnd.ms-excel' || fileType === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
				// You can implement downloading Excel files
				const downloadLink = document.createElement('a');
				downloadLink.href = URL.createObjectURL(selectedFile);
				downloadLink.download = selectedFile.name;
				downloadLink.click();
			} else if (fileType.startsWith('image/')) {
				// Display images in a new tab
				window.open(URL.createObjectURL(selectedFile), '_blank');
			} else {
				alert('Unsupported file type for viewing.');
			}
		});
		const deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		deleteButton.addEventListener('click', function() {
			// Implement delete functionality
			listItem.remove(); // Remove the list item from the UI
			// You can also send a request to the server to delete the file
		});
		listItem.appendChild(viewButton);
		listItem.appendChild(deleteButton);
		fileList.appendChild(listItem);
	} else {
		alert('Please select a file to upload.');
	}
});