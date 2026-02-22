let index = 0;
const track = document.querySelector('.banner-track');
const total = track.children.length;

function slideTo(i) {
  track.style.transform = `translateX(-${i * 100}%)`;
}

document.querySelector('.slide-btn.right').onclick = () => {
  index = (index + 1) % total;
  slideTo(index);
};

document.querySelector('.slide-btn.left').onclick = () => {
  index = (index - 1 + total) % total;
  slideTo(index);
};

setInterval(() => {
  index = (index + 1) % total;
  slideTo(index);
}, 5000);
