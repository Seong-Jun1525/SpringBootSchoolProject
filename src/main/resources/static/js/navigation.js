// 네비게이션 링크 클릭 이벤트 처리
document.addEventListener("DOMContentLoaded", function() {
    var navLinks = document.querySelectorAll(".nav-link");
    navLinks.forEach(function(link) {
        link.addEventListener("click", function(event) {
            event.preventDefault();
            var url = this.getAttribute("href");
            loadContent(url);
        });
    });
});

// AJAX 요청 및 페이지 업데이트
function loadContent(url) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                updateContent(xhr.responseText);
            } else {
                console.error("Error loading content. Status code: " + xhr.status);
            }
        }
    };
    xhr.send();
}

// 페이지 업데이트
function updateContent(html) {
    var mainContent = document.getElementById("main-content");
    mainContent.innerHTML = html;
}
