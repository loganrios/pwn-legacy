import React from "react";
import ChapterNote from "./ChapterNote";

export default {
  title: "ChapterNote",
  component: ChapterNote,
};

const Template = (args) => <ChapterNote {...args} />;

export const Visible = Template.bind({});
Visible.args = {
  text: "This is header text. These are lots of other words to test if this component is working properly.",
  isVisible: true,
};

export const NotVisible = Template.bind({});
NotVisible.args = {
  text: "This is header text",
  isVisible: false,
};
