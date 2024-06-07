document.addEventListener("DOMContentLoaded", function() {
    // 드롭다운 메뉴 토글
    var dropdownToggle = document.getElementById("dropdownMenuButton");
    dropdownToggle.addEventListener("click", function(event) {
        event.preventDefault();
        var dropdownMenu = document.querySelector(".dropdown-menu");
        dropdownMenu.classList.toggle("show");
    });

    // 클릭 이벤트가 드롭다운 메뉴 외부를 클릭할 때 드롭다운을 닫습니다.
    document.addEventListener("click", function(event) {
        var isClickInside = dropdownToggle.contains(event.target);
        var dropdownMenu = document.querySelector(".dropdown-menu");
        if (!isClickInside && dropdownMenu.classList.contains("show")) {
            dropdownMenu.classList.remove("show");
        }
    });
});

function loadContent(event) {
    event.preventDefault();
    const url = event.target.getAttribute('data-url');
    
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'text/html'
        }
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('main-content').innerHTML = data;
    })
    .catch(error => {
        console.error('Error fetching content:', error);
    });
}
