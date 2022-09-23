import React from "react";
import WorkChapterList from "./WorkChapterList";

export default {
  title: "WorkChapterList",
  component: WorkChapterList,
};

const Template = (args) => <WorkChapterList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  chapters: [
    {
      id: 1,
      title: "Of Stocks and Demons",
      date: "9/4/2022, 12:00 PM",
      words: 472,
      views: 60,
      comment: "Taz",
      onEdit: () => console.log("change me"),
      onDelete: () => console.log("deleted"),
    },
    {
      id: 2,
      title: "Born to be rich",
      date: "9/5/2022, 7:26 PM",
      words: 364,
      views: 42,
      comment: "Ash",
      onEdit: () => console.log("i am inevitable"),
      onDelete: () => console.log("thanos snap"),
    },
    {
      id: 4,
      title: "Ash in a broken world",
      date: "9/7/2022, 8:00 AM",
      words: 531,
      views: 25,
      comment: "Devrey",
      onEdit: () => console.log("i am iron man"),
      onDelete: () => console.log("restored?"),
    },
    {
      id: 3,
      title: "Queen",
      date: "9/6/2022, 4:15 PM",
      words: 496,
      views: 34,
      comment: "Leif",
      onEdit: () => console.log("why is gamora"),
      onDelete: () => console.log("nobody knows"),
    },
  ],
};
