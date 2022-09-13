import React from "react";
import WorksList from "./WorksList";

export default {
  title: "WorksList",
  component: WorksList,
  argTypes: {
    onTabSelect: { action: "Check out my Rolodex" },
    onDashNav: { action: "Dash is the strongest avenger #Incredibles" },
    onPageNav: { action: "A real page-turner" },
  },
};

const Template = (args) => <WorksList {...args} />;

export const Ongoing = Template.bind({});
Ongoing.args = {
  viewTab: 0,
  workTitle: "test",
  ongoing: [
    {
      value: "0",
      label: "Work 1",
      cover:
        "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
      // onDashnav: () => (js/console.log "Hiii"),
      // onPageNav: () => (js/console.log "Page"),
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/91NxYvUNf6L.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://static.wikia.nocookie.net/onepiece/images/2/2f/Volume_2.png/revision/latest?cb=20130115021820",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/81sVMmbkafL.jpg",
    },
  ],
  completed: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://m.media-amazon.com/images/I/51FEKMNJTbL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/616uqDQU4aL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51JKA15JrDL._SX328_BO1,204,203,200_.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51PgVeGBsbL._SX331_BO1,204,203,200_.jpg",
    },
  ],
  hiatus: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://images-na.ssl-images-amazon.com/images/I/61LVJN2aIoL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://m.media-amazon.com/images/I/61IH90LyydL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://d28hgpri8am2if.cloudfront.net/book_images/cvr9781591161875_9781591161875_hr.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/71DAeAJPU-L.jpg",
    },
  ],
};

export const Completed = Template.bind({});
Completed.args = {
  viewTab: 1,
  ongoing: [
    {
      value: "0",
      label: "Work 1",
      cover:
        "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/91NxYvUNf6L.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://static.wikia.nocookie.net/onepiece/images/2/2f/Volume_2.png/revision/latest?cb=20130115021820",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/81sVMmbkafL.jpg",
    },
  ],
  completed: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://m.media-amazon.com/images/I/51FEKMNJTbL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/616uqDQU4aL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51JKA15JrDL._SX328_BO1,204,203,200_.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51PgVeGBsbL._SX331_BO1,204,203,200_.jpg",
    },
  ],
  hiatus: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://images-na.ssl-images-amazon.com/images/I/61LVJN2aIoL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://m.media-amazon.com/images/I/61IH90LyydL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://d28hgpri8am2if.cloudfront.net/book_images/cvr9781591161875_9781591161875_hr.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/71DAeAJPU-L.jpg",
    },
  ],
};

export const Hiatus = Template.bind({});
Hiatus.args = {
  viewTab: 2,
  ongoing: [
    {
      value: "0",
      label: "Work 1",
      cover:
        "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/91NxYvUNf6L.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://static.wikia.nocookie.net/onepiece/images/2/2f/Volume_2.png/revision/latest?cb=20130115021820",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/81sVMmbkafL.jpg",
    },
  ],
  completed: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://m.media-amazon.com/images/I/51FEKMNJTbL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/616uqDQU4aL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51JKA15JrDL._SX328_BO1,204,203,200_.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51PgVeGBsbL._SX331_BO1,204,203,200_.jpg",
    },
  ],
  hiatus: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://images-na.ssl-images-amazon.com/images/I/61LVJN2aIoL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://m.media-amazon.com/images/I/61IH90LyydL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://d28hgpri8am2if.cloudfront.net/book_images/cvr9781591161875_9781591161875_hr.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/71DAeAJPU-L.jpg",
    },
  ],
};

export const Null = Template.bind({});
Null.args = {
  viewTab: 3,
  ongoing: [
    {
      value: "0",
      label: "Work 1",
      cover:
        "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/91NxYvUNf6L.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://static.wikia.nocookie.net/onepiece/images/2/2f/Volume_2.png/revision/latest?cb=20130115021820",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/81sVMmbkafL.jpg",
    },
  ],
  completed: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://m.media-amazon.com/images/I/51FEKMNJTbL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://images-na.ssl-images-amazon.com/images/I/616uqDQU4aL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51JKA15JrDL._SX328_BO1,204,203,200_.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover:
        "https://images-na.ssl-images-amazon.com/images/I/51PgVeGBsbL._SX331_BO1,204,203,200_.jpg",
    },
  ],
  hiatus: [
    {
      value: "0",
      label: "Work 1",
      cover: "https://images-na.ssl-images-amazon.com/images/I/61LVJN2aIoL.jpg",
    },
    {
      value: "1",
      label: "Work 2",
      cover: "https://m.media-amazon.com/images/I/61IH90LyydL.jpg",
    },
    {
      value: "2",
      label: "Work 3",
      cover:
        "https://d28hgpri8am2if.cloudfront.net/book_images/cvr9781591161875_9781591161875_hr.jpg",
    },
    {
      value: "3",
      label: "Work 4",
      cover: "https://images-na.ssl-images-amazon.com/images/I/71DAeAJPU-L.jpg",
    },
  ],
};
